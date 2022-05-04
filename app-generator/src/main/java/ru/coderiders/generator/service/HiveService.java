package ru.coderiders.generator.service;

import lombok.NonNull;
import org.springframework.web.bind.annotation.PathVariable;
import ru.coderiders.commons.rest.dto.GeneratorHiveRqDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;
import ru.coderiders.generator.entity.BeeFamily;
import ru.coderiders.generator.entity.Hive;

import java.util.List;

public interface HiveService {
    HiveSnapshotDto createHiveSnapshot(Hive hive);

    List<Hive> findAllWithBeeFamilies();

    void create(@NonNull GeneratorHiveRqDto generatorHiveRqDto);

    void updateDelta(@NonNull Long id, @NonNull Double delta);

    void clearHoney(@NonNull Long id);

    void updateOverheatedStatus(@NonNull Long id, @NonNull Boolean isOverheated);

    void saveFamily(@NonNull Long id, @NonNull BeeFamily beeFamily);

    void releaseFamily(@NonNull Long id);

    boolean isOccupied(@NonNull Long id);
}
