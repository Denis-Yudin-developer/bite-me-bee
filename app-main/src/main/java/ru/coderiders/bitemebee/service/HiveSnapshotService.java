package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import ru.coderiders.bitemebee.entity.HiveSnapshot;
import ru.coderiders.commons.rest.dto.HiveSnapshotRqDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotRsDto;

import java.util.List;

public interface HiveSnapshotService {
    List<HiveSnapshotRsDto> getSnapshots(@NonNull HiveSnapshotRqDto hiveSnapshotRqDto);

    HiveSnapshot createSnapshot(@NonNull HiveSnapshotRsDto hiveSnapshotRsDto);
}
