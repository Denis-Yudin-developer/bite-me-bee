package ru.coderiders.bitemebee.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.bitemebee.entity.BeeFamily;
import ru.coderiders.bitemebee.mapper.BeeFamilyMapper;
import ru.coderiders.bitemebee.repository.BeeFamilyRepository;
import ru.coderiders.bitemebee.rest.dto.BeeFamilyNoteRqDto;
import ru.coderiders.bitemebee.rest.dto.BeeFamilyRqDto;
import ru.coderiders.bitemebee.rest.dto.BeeFamilyRsDto;
import ru.coderiders.bitemebee.service.BeeFamilyService;
import ru.coderiders.commons.rest.api.generator.BeeFamilyFeignApi;
import ru.coderiders.commons.rest.dto.GeneratorFamilyRqDto;
import ru.coderiders.commons.rest.exception.BadRequestException;
import ru.coderiders.commons.rest.exception.NotFoundException;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class BeeFamilyServiceImpl implements BeeFamilyService {
    private static final String BEE_FAMILY_NOT_FOUND = "Пчелиная семья с id=%s не найдена";
    private static final String HIVE_IS_OCCUPIED = "Улей занят другой пчелиной семьёй";
    private final BeeFamilyRepository beeFamilyRepository;
    private final BeeFamilyMapper beeFamilyMapper;
    private final HiveServiceImpl hiveService;
    private final BeeTypeServiceImpl beeTypeService;
    private final BeeFamilyFeignApi beeFamilyFeignApi;

    @Override
    @Transactional
    public BeeFamilyRsDto create(@NonNull BeeFamilyRqDto beeFamilyRqDto) {
        log.debug("Запрос на создание новой пчелиной семьи, beeFamilyRqDto = {}", beeFamilyRqDto);
        var hiveId = beeFamilyRqDto.getHiveId();
        var beeTypeId = beeFamilyRqDto.getBeeTypeId();
        try {
            hiveService.getById(hiveId);
            beeTypeService.getById(beeTypeId);
        } catch (NotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }
        if (hiveService.isOccupied(hiveId)) {
            log.warn("Улей уже занят, id = {}", hiveId);
            throw new BadRequestException(HIVE_IS_OCCUPIED);
        }
        long totalPopulation = beeFamilyRqDto.getDronePopulation() +
                beeFamilyRqDto.getQueenPopulation() +
                beeFamilyRqDto.getWorkerPopulation();
        BeeFamily toCreate = beeFamilyMapper.toEntity(beeFamilyRqDto);
        toCreate.setPopulation(totalPopulation);
        toCreate.setCreatedAt(Instant.now());
        BeeFamily created = beeFamilyRepository.save(toCreate);
        log.debug("Пчелиная семья успешно добавлена в улей, id = {}, toCreate = {}", hiveId, toCreate);
        GeneratorFamilyRqDto generatorFamilyRqDto = GeneratorFamilyRqDto.builder()
                .id(created.getId())
                .hiveId(beeFamilyRqDto.getHiveId())
                .dronePopulation(created.getDronePopulation())
                .workerPopulation(created.getWorkerPopulation())
                .queenPopulation(created.getQueenPopulation())
                .population(created.getPopulation())
                .diseaseResistance(created.getBeeType().getDiseaseResistance())
                .honeyProductivity(created.getBeeType().getHoneyProductivity())
                .eggProductivity(created.getBeeType().getEggProductivity())
                .build();
        beeFamilyFeignApi.addFamily(generatorFamilyRqDto);
        return beeFamilyMapper.toDto(created);
    }

    @Override
    @Transactional
    public BeeFamilyRsDto getById(@NonNull Long id) {
        log.debug("Запрос на получение пчелиной семьи по id = {}", id);
        return beeFamilyRepository.findById(id)
                .map(beeFamilyMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_FAMILY_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public Page<BeeFamilyRsDto> getAll(@NonNull Pageable pageable) {
        log.debug("Запрос на получение всех пчелиных семьей, pageable = {}", pageable);
        return beeFamilyRepository.findAll(pageable)
                .map(beeFamilyMapper::toDto);
    }

    @Override
    @Transactional
    public BeeFamilyRsDto update(@NonNull Long id, @NonNull BeeFamilyNoteRqDto beeFamilyNoteRqDto) {
        log.debug("Запрос на получение обновление пчелиной семьи по id = {}, beeFamilyNoteRqDto = {}", id, beeFamilyNoteRqDto);
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
    public void release(@NonNull Long id) {
        log.debug("Запрос на выселение пчелиной семьи по id = {}", id);
        beeFamilyRepository.findById(id)
                .ifPresentOrElse(found -> found.setIsAlive(false),
                        () -> {
                            throw new NotFoundException(String.format(BEE_FAMILY_NOT_FOUND, id));
                        });
        beeFamilyFeignApi.deleteById(id);
    }
}
