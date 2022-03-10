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
        log.info("Создание записи о пчелиной семье, beeFamilyRqDto = {}", beeFamilyRqDto);

        BeeFamily beeFamilySrc = beeFamilyMapper.toEntity(beeFamilyRqDto);

        if (hiveService.isOccupied(beeFamilySrc.getHive().getId())) {
            log.info("Улей уже занят...");
            throw new BadRequestException(HIVE_IS_OCCUPIED);
        }
        log.info("Улей свободен, продолжаем...");

        BeeFamily beeFamilyDst = beeFamilyRepository.save(beeFamilySrc);
        log.info("Новая пчелиная семья успешно добавлена в улей");

        return beeFamilyMapper.toDto(beeFamilyDst);
    }

    @Override
    @Transactional
    public BeeFamilyRsDto getById(@NonNull Long id) {
        log.info("Получение записи о пчелиной семье по идентификатору, id = {}", id);

        return beeFamilyRepository.findById(id)
                .map(beeFamilyMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_FAMILY_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public Page<BeeFamilyRsDto> getAll(@NonNull Pageable pageable) {
        log.info("Получение всех записей о пчелиных семьях, pageable = {}", pageable);

        return beeFamilyRepository.findAll(pageable)
                .map(beeFamilyMapper::toDto);
    }

    @Override
    @Transactional
    public BeeFamilyRsDto update(@NonNull Long id, @NonNull BeeFamilyNoteRqDto beeFamilyNoteRqDto) {
        log.info("Обновление заметки о пчелиной семье по идентификатору, id = {}, beeFamilyNoteRqDto = {}", id, beeFamilyNoteRqDto);

        final var currentFamily = beeFamilyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_FAMILY_NOT_FOUND, id)));

        currentFamily.setNote(beeFamilyNoteRqDto.getNote());
        return beeFamilyMapper.toDto(currentFamily);
    }

    @Override
    @Transactional
    public BeeFamilyRsDto release(@NonNull Long id) {
        log.info("Выпускаем пчелиную семью на волю по идентификатору, id = {}", id);

        final var currentFamily = beeFamilyRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_FAMILY_NOT_FOUND, id)));

        currentFamily.setIsAlive(false);
        return beeFamilyMapper.toDto(currentFamily);
    }

    @Override
    @Transactional
    public void deleteById(@NonNull Long id) {
        log.info("Удаление записи о пчелиной семье по идентификатору, id = {}", id);

        beeFamilyRepository.findById(id)
                .ifPresentOrElse(
                        beeFamilyRepository::delete,
                        () -> {
                            throw new NotFoundException(String.format(BEE_FAMILY_NOT_FOUND, id));
                        });
    }
}
