package ru.coderiders.generator.rest.api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.coderiders.commons.rest.dto.GeneratorFamilyRqDto;
import ru.coderiders.generator.rest.api.BeeFamilyApi;
import ru.coderiders.generator.service.impl.BeeFamilyServiceImpl;

@RestController
@RequiredArgsConstructor
public class BeeFamilyController implements BeeFamilyApi {
    private final BeeFamilyServiceImpl beeFamilyService;

    @Override
    public void addFamily(GeneratorFamilyRqDto generatorFamilyRqDto) {
        beeFamilyService.create(generatorFamilyRqDto);
    }

    @Override
    public void deleteById(Long id) {
        beeFamilyService.delete(id);
    }

    @Override
    public void updateInfectedStatus(Long id, Boolean isInfected) {
        beeFamilyService.updateInfectedStatus(id, isInfected);
    }
}
