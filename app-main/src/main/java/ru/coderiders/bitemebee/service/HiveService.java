package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coderiders.bitemebee.entity.Hive;
import ru.coderiders.bitemebee.entity.HiveSnapshot;
import ru.coderiders.bitemebee.rest.dto.HiveRqDto;
import ru.coderiders.bitemebee.rest.dto.HiveRsDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotRqDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotRsDto;

import java.util.List;

public interface HiveService {
    List<HiveSnapshotRsDto> getSnapshots(@NonNull HiveSnapshotRqDto hiveSnapshotRqDto);

    Page<HiveRsDto> getAll(@NonNull Pageable pageable);

    HiveSnapshot createSnapshot(@NonNull HiveSnapshotRsDto hiveSnapshotRsDto);

    Hive getEntityById(@NonNull Long id);

    HiveRsDto getById(@NonNull Long id);

    HiveRsDto create(@NonNull HiveRqDto hiveRqDto);

    HiveRsDto update(@NonNull Long id, @NonNull HiveRqDto hiveRqDto);

    void updateHoney(@NonNull Long id, @NonNull Double honeyIncrease);

    void deleteById(@NonNull Long id);

    boolean isOccupied(@NonNull Long id);

    boolean isExists(@NonNull Long id);
}
