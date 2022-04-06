package ru.coderiders.bitemebee.rabbit;

import lombok.NonNull;
import ru.coderiders.commons.rest.dto.HiveSnapshotRsDto;

public interface SnapshotProcessor {
    void processHiveSnapshot(@NonNull HiveSnapshotRsDto hiveSnapshotRsDto);
}
