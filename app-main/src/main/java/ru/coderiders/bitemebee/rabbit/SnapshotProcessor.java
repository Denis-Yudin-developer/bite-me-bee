package ru.coderiders.bitemebee.rabbit;

import lombok.NonNull;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

public interface SnapshotProcessor {
    void processHiveSnapshot(@NonNull HiveSnapshotDto snapshot);
    void processBeeFamilySnapshot(@NonNull BeeFamilySnapshotDto beeFamilySnapshotGeneratorDto);
}
