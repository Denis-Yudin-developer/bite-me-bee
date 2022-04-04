package ru.coderiders.Generator.rest.api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.coderiders.commons.rest.dto.GeneratorHiveRqDto;
import ru.coderiders.Generator.rest.api.HiveApi;
import ru.coderiders.Generator.service.HiveService;

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
    public void updateOverheatedStatus(Long id, Boolean isOverheated) {
        hiveService.updateOverheatedStatus(id, isOverheated);
    }
}
