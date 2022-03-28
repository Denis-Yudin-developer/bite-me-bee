package ru.coderiders.Generator.rest.api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.coderiders.Generator.rest.api.BeeFamilyApi;
import ru.coderiders.Generator.service.BeeFamilyService;
import ru.coderiders.Library.rest.dto.GeneratorFamilyRqDto;

@RestController
@RequiredArgsConstructor
public class BeeFamilyController implements BeeFamilyApi {

    private final BeeFamilyService beeFamilyService;

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
