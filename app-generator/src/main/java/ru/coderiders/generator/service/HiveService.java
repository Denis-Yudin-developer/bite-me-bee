package ru.coderiders.Generator.service;

import lombok.NonNull;
import ru.coderiders.commons.rest.dto.GeneratorHiveRqDto;
import ru.coderiders.Generator.entity.BeeFamily;

public interface HiveService {
    void create(@NonNull GeneratorHiveRqDto generatorHiveRqDto);

    void updateDelta(@NonNull Long id, @NonNull Double delta);

    void updateOverheatedStatus(@NonNull Long id, @NonNull Boolean isOverheated);

    void saveFamily(@NonNull Long id, @NonNull BeeFamily beeFamily);

    void releaseFamily(@NonNull Long id);

    boolean isOccupied(@NonNull Long id);
}
