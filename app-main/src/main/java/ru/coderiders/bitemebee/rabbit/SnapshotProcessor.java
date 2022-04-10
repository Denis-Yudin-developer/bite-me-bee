package ru.coderiders.bitemebee.rabbit;

import lombok.NonNull;
import ru.coderiders.commons.rest.dto.HiveSnapshotGeneratorDto;

public interface SnapshotProcessor {
    void processHiveSnapshot(@NonNull HiveSnapshotGeneratorDto hiveSnapshotGeneratorDto);
}
