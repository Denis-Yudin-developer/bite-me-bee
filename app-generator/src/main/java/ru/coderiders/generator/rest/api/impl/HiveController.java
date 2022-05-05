package ru.coderiders.generator.rest.api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.coderiders.commons.rest.dto.GeneratorHiveRqDto;
import ru.coderiders.generator.rest.api.HiveApi;
import ru.coderiders.generator.service.HiveService;

@RestController
@RequiredArgsConstructor
public class HiveController implements HiveApi {
    private final HiveService hiveService;

    @Override
    public void addHive(GeneratorHiveRqDto generatorHiveRqDto) {
        hiveService.create(generatorHiveRqDto);
    }

    @Override
    public void updateDelta(Long id, Double delta) {
        hiveService.updateDelta(id, delta);
    }

    @Override
    public void clearHoney(Long id) {
        hiveService.clearHoney(id);
    }

    @Override
    public void updateOverheatedStatus(Long id, Boolean isOverheated) {
        hiveService.updateOverheatedStatus(id, isOverheated);
    }

    @Override
    public void updateChilledStatus(Long id, Boolean isChilled) {
        hiveService.updateChilledStatus(id, isChilled);
    }
}
