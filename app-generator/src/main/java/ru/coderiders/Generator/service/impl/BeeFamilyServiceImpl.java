package ru.coderiders.Generator.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.Generator.entity.BeeFamily;
import ru.coderiders.Generator.repository.BeeFamilyRepository;
import ru.coderiders.Generator.service.BeeFamilyService;
import ru.coderiders.Library.rest.dto.GeneratorFamilyRqDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeeFamilyServiceImpl implements BeeFamilyService {

    private final BeeFamilyRepository beeFamilyRepository;
    private final HiveServiceImpl hiveService;

    @Override
    @Transactional
    public void create(GeneratorFamilyRqDto generatorFamilyRqDto) {
        log.debug("Запрос на подселение семьи в генераторе, id = {}", generatorFamilyRqDto.getId());

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
        beeFamilyRepository.save(toCreate);

        hiveService.saveFamily(generatorFamilyRqDto.getHiveId(), toCreate);
    }

    @Override
    @Transactional
    public void delete(@NonNull Long id) {
        log.debug("Запрос на выселение семьи в генераторе, id = {}", id);

        beeFamilyRepository.findById(id)
                .ifPresent(beeFamilyRepository::delete);
    }

    @Override
    @Transactional
    public void updateInfectedStatus(@NonNull Long id, @NonNull Boolean isInfected) {
        log.debug("Запрос на изменение здоровья семьи в генераторе, id = {}, isInfected = {}", id, isInfected);

        beeFamilyRepository.findById(id)
                .map(found -> {
                    found.setIsInfected(isInfected);
                    return found;
                });
    }
}
