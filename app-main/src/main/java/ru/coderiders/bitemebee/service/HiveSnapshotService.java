package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coderiders.bitemebee.entity.HiveSnapshot;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotRqDto;

public interface HiveSnapshotService {
    Page<HiveSnapshotDto> getSnapshots(@NonNull Pageable pageable, @NonNull HiveSnapshotRqDto hiveSnapshotRqDto);

    HiveSnapshot createSnapshot(@NonNull HiveSnapshotDto hiveSnapshotRsDto);
}
