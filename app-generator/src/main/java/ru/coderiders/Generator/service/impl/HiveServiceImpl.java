package ru.coderiders.Generator.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.Generator.entity.BeeFamily;
import ru.coderiders.Generator.entity.Hive;
import ru.coderiders.Generator.repository.HiveRepository;
import ru.coderiders.Generator.service.HiveService;
import ru.coderiders.Library.rest.dto.GeneratorHiveRqDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class HiveServiceImpl implements HiveService {

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
                });
    }

    @Override
    @Transactional
    public void updateOverheatedStatus(@NonNull Long id, @NonNull Boolean isOverheated) {
        log.debug("Запрос на изменение перегрева улья в генераторе, id = {}", id);

        hiveRepository.findById(id)
                .map(found -> {
                    found.setIsOverheated(isOverheated);
                    return found;
                });
    }

    @Override
    public void saveFamily(@NonNull Long id, @NonNull BeeFamily beeFamily) {
        log.debug("Запрос на присвоение семьи улью в генераторе, id = {}", id);

        hiveRepository.findById(id)
                .map(found -> {
                    found.setBeeFamily(beeFamily);
                    return found;
                });
    }
}
