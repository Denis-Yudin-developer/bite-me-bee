package ru.coderiders.Generator.service;

import lombok.NonNull;
import ru.coderiders.Generator.rest.dto.GeneratorFamilyRqDto;

public interface BeeFamilyService {

    void create(GeneratorFamilyRqDto generatorFamilyRqDto);

    void delete(@NonNull Long id);

    void updateInfectedStatus(@NonNull Long id, @NonNull Boolean isInfected);
}

