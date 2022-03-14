package com.example.BiteMeBee.service.impl;

import com.example.BiteMeBee.entity.BeeFamily;
import com.example.BiteMeBee.mapper.BeeFamilyMapper;
import com.example.BiteMeBee.repository.BeeFamilyRepository;
import com.example.BiteMeBee.rest.dto.BeeFamilyNoteRqDto;
import com.example.BiteMeBee.rest.dto.BeeFamilyRqDto;
import com.example.BiteMeBee.rest.dto.BeeFamilyRsDto;
import com.example.BiteMeBee.rest.exception.BadRequestException;
import com.example.BiteMeBee.service.BeeFamilyService;
import com.example.BiteMeBee.service.HiveService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeeFamilyServiceImpl implements BeeFamilyService {

    private static final String BEE_FAMILY_NOT_FOUND = "Пчелиная семья с id=%s не найдена";
    private static final String HIVE_IS_OCCUPIED = "Улей занят другой пчелиной семьёй";

    private final BeeFamilyRepository beeFamilyRepository;
    private final BeeFamilyMapper beeFamilyMapper;
    private final HiveService hiveService;

    @Override
    @Transactional
    public BeeFamilyRsDto create(@NonNull BeeFamilyRqDto beeFamilyRqDto) {
        log.debug("Создание записи о пчелиной семье, beeFamilyRqDto = {}", beeFamilyRqDto);

        var hiveId = beeFamilyRqDto.getHiveId();
        if (hiveService.isOccupied(hiveId)) {
            log.warn("Улей уже занят, id = {}", hiveId);
            throw new BadRequestException(HIVE_IS_OCCUPIED);
        }

        long totalPopulation = beeFamilyRqDto.getDronePopulation() +
                beeFamilyRqDto.getQueenPopulation() +
                beeFamilyRqDto.getWorkerPopulation();
        BeeFamily toCreate = beeFamilyMapper.toEntity(beeFamilyRqDto);
        toCreate.setPopulation(totalPopulation);
        BeeFamily created = beeFamilyRepository.save(toCreate);
        log.debug("Пчелиная семья успешно добавлена в улей, id = {}, toCreate = {}", hiveId, toCreate);

        return beeFamilyMapper.toDto(created);
    }

    @Override
    @Transactional
    public BeeFamilyRsDto getById(@NonNull Long id) {
        log.debug("Получение записи о пчелиной семье по идентификатору, id = {}", id);

        return beeFamilyRepository.findById(id)
                .map(beeFamilyMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_FAMILY_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public Page<BeeFamilyRsDto> getAll(@NonNull Pageable pageable) {
        log.debug("Получение всех записей о пчелиных семьях, pageable = {}", pageable);

        return beeFamilyRepository.findAll(pageable)
                .map(beeFamilyMapper::toDto);
    }

    @Override
    @Transactional
    public BeeFamilyRsDto update(@NonNull Long id, @NonNull BeeFamilyNoteRqDto beeFamilyNoteRqDto) {
        log.debug("Обновление заметки о пчелиной семье, id = {}, beeFamilyNoteRqDto = {}", id, beeFamilyNoteRqDto);

        return beeFamilyRepository.findById(id)
                .map(found -> {
                    found.setNote(beeFamilyNoteRqDto.getNote());
                    return found;
                })
                .map(beeFamilyMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_FAMILY_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public BeeFamilyRsDto release(@NonNull Long id) {
        log.debug("Выпускаем пчелиную семью на волю по идентификатору, id = {}", id);

        return beeFamilyRepository.findById(id)
                .map(found -> {
                    found.setIsAlive(false);
                    return found;
                })
                .map(beeFamilyMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_FAMILY_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public void deleteById(@NonNull Long id) {
        log.debug("Удаление записи о пчелиной семье по идентификатору, id = {}", id);

        beeFamilyRepository.findById(id)
                .ifPresent(beeFamilyRepository::delete);
    }
}
