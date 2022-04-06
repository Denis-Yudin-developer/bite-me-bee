package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import ru.coderiders.bitemebee.entity.HiveSnapshot;
import ru.coderiders.commons.rest.dto.HiveSnapshotGeneratorDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotRqDto;

import java.util.List;

public interface HiveSnapshotService {
    List<HiveSnapshotGeneratorDto> getSnapshots(@NonNull HiveSnapshotRqDto hiveSnapshotRqDto);

    HiveSnapshot createSnapshot(@NonNull HiveSnapshotGeneratorDto hiveSnapshotRsDto);
}
