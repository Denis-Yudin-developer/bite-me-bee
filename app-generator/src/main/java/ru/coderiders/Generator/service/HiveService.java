package ru.coderiders.Generator.service;

import lombok.NonNull;
import ru.coderiders.Generator.entity.BeeFamily;
import ru.coderiders.Library.rest.dto.GeneratorHiveRqDto;

public interface HiveService {

    void create(@NonNull GeneratorHiveRqDto generatorHiveRqDto);

    void updateDelta(@NonNull Long id, @NonNull Double delta);

    void updateOverheatedStatus(@NonNull Long id, @NonNull Boolean isOverheated);

    void saveFamily(@NonNull Long id, @NonNull BeeFamily beeFamily);

    boolean isOccupied(@NonNull Long id);
}
