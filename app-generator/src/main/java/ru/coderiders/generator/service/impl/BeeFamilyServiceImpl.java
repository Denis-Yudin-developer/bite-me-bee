package ru.coderiders.generator.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.commons.rest.dto.GeneratorFamilyRqDto;
import ru.coderiders.commons.rest.exception.BadRequestException;
import ru.coderiders.commons.rest.exception.NotFoundException;
import ru.coderiders.generator.entity.BeeFamily;
import ru.coderiders.generator.repository.BeeFamilyRepository;
import ru.coderiders.generator.service.BeeFamilyService;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeeFamilyServiceImpl implements BeeFamilyService {
    private static final String BEE_FAMILY_NOT_FOUND = "Пчелиная семья с id=%s не найдена";
    private static final String HIVE_IS_OCCUPIED = "Улей занят другой пчелиной семьёй";
    private final BeeFamilyRepository beeFamilyRepository;
    private final HiveServiceImpl hiveService;

    @Override
    @Transactional
    public void create(GeneratorFamilyRqDto generatorFamilyRqDto) {
        log.debug("Запрос на подселение семьи в генераторе, id = {}", generatorFamilyRqDto.getId());
        var hiveId = generatorFamilyRqDto.getHiveId();
        if (hiveService.isOccupied(hiveId)) {
            log.warn("Улей уже занят, id = {}", hiveId);
            throw new BadRequestException(HIVE_IS_OCCUPIED);
        }
        BeeFamily toCreate = BeeFamily.builder()
                .id(generatorFamilyRqDto.getId())
                .dronePopulation(generatorFamilyRqDto.getDronePopulation())
                .workerPopulation(generatorFamilyRqDto.getWorkerPopulation())
                .queenPopulation(generatorFamilyRqDto.getQueenPopulation())
                .population(generatorFamilyRqDto.getPopulation())
                .diseaseResistance(generatorFamilyRqDto.getDiseaseResistance())
                .honeyProductivity(generatorFamilyRqDto.getHoneyProductivity())
                .eggProductivity(generatorFamilyRqDto.getEggProductivity())
                .build();
        BeeFamily toSaveInHive = beeFamilyRepository.save(toCreate);
        hiveService.saveFamily(generatorFamilyRqDto.getHiveId(), toSaveInHive);
    }

    @Override
    @Transactional
    public void delete(@NonNull Long id) {
        log.debug("Запрос на выселение семьи в генераторе, id = {}", id);
        hiveService.releaseFamily(id);
    }

    @Override
    @Transactional
    public void updateInfectedStatus(@NonNull Long id, @NonNull Boolean isInfected) {
        log.debug("Запрос на изменение здоровья семьи в генераторе, id = {}, isInfected = {}", id, isInfected);
        beeFamilyRepository.findById(id)
                .map(found -> {
                    found.setIsInfected(isInfected);
                    return found;
                })
                .orElseThrow(() -> new NotFoundException(String.format(BEE_FAMILY_NOT_FOUND, id)));
    }
}
