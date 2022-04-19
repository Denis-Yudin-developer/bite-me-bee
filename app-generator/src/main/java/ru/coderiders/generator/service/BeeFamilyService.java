package ru.coderiders.generator.service;

import lombok.NonNull;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;
import ru.coderiders.commons.rest.dto.GeneratorFamilyRqDto;
import ru.coderiders.generator.entity.BeeFamily;

import java.util.List;

public interface BeeFamilyService {
    BeeFamilySnapshotDto createBeeFamilySnapshot(BeeFamily beeFamily);

    List<BeeFamily> findAll();

    void create(GeneratorFamilyRqDto generatorFamilyRqDto);

    void delete(@NonNull Long id);

    void updateInfectedStatus(@NonNull Long id, @NonNull Boolean isInfected);

    void updateDelta(@NonNull Long id, @NonNull Double delta);
}

