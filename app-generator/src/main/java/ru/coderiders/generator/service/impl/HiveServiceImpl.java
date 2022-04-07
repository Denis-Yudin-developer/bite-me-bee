package ru.coderiders.generator.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.commons.rest.dto.GeneratorHiveRqDto;
import ru.coderiders.commons.rest.exception.NotFoundException;
import ru.coderiders.generator.entity.BeeFamily;
import ru.coderiders.generator.entity.Hive;
import ru.coderiders.generator.repository.HiveRepository;
import ru.coderiders.generator.service.HiveService;

@Slf4j
@Service
@RequiredArgsConstructor
public class HiveServiceImpl implements HiveService {
    private final String HIVE_NOT_FOUND = "Улей с id=%s не найден";
    private final HiveRepository hiveRepository;

    @Override
    @Transactional
    public void create(@NonNull GeneratorHiveRqDto generatorHiveRqDto) {
        log.debug("Запрос на создание нового улья в генераторе, id = {}", generatorHiveRqDto.getId());
        Hive toCreate = Hive.builder()
                .id(generatorHiveRqDto.getId())
                .honeyCapacity(generatorHiveRqDto.getHoneyCapacity()).build();
        hiveRepository.save(toCreate);
    }

    @Override
    @Transactional
    public void updateDelta(@NonNull Long id, @NonNull Double delta) {
        log.debug("Запрос на изменение дельты улья в генераторе, id = {}", id);
        hiveRepository.findById(id)
                .map(found -> {
                    found.setDelta(delta);
                    return found;
                })
                .orElseThrow(() -> new NotFoundException(String.format(HIVE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public void updateOverheatedStatus(@NonNull Long id, @NonNull Boolean isOverheated) {
        log.debug("Запрос на изменение перегрева улья в генераторе, id = {}", id);
        hiveRepository.findById(id)
                .map(found -> {
                    found.setIsOverheated(isOverheated);
                    return found;
                })
                .orElseThrow(() -> new NotFoundException(String.format(HIVE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public void saveFamily(@NonNull Long id, @NonNull BeeFamily beeFamily) {
        log.debug("Запрос на присвоение семьи улью в генераторе, id = {}", id);
        hiveRepository.findById(id)
                .map(found -> {
                    found.setBeeFamily(beeFamily);
                    return found;
                })
                .orElseThrow(() -> new NotFoundException(String.format(HIVE_NOT_FOUND, id)));
    }

    @Override
    public void releaseFamily(@NonNull Long id) {
        hiveRepository.findByBeeFamilyId(id)
                .map(found -> {
                    found.setBeeFamily(null);
                    return found;
                })
                .orElseThrow(() -> new NotFoundException(String.format(HIVE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public boolean isOccupied(@NonNull Long id) {
        log.debug("Проверяем, является ли улей занятым живой пчелиной семьёй, id = {}", id);
        return hiveRepository.findById(id)
                .map(found -> found.getBeeFamily() != null)
                .orElseThrow(() -> new NotFoundException(String.format(HIVE_NOT_FOUND, id)));
    }
}