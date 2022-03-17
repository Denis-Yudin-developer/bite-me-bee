package ru.coderiders.Main.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coderiders.Main.rest.dto.HiveRqDto;
import ru.coderiders.Main.rest.dto.HiveRsDto;
import ru.coderiders.Main.rest.dto.HiveSnapshotRqDto;
import ru.coderiders.Main.rest.dto.HiveSnapshotRsDto;

import java.util.List;

public interface HiveService {

    List<HiveSnapshotRsDto> getSnapshots(@NonNull HiveSnapshotRqDto hiveSnapshotRqDto);

    Page<HiveRsDto> getAll(@NonNull Pageable pageable);

    HiveRsDto getById(@NonNull Long id);

    HiveRsDto create(@NonNull HiveRqDto hiveRqDto);

    HiveRsDto update(@NonNull Long id, @NonNull HiveRqDto hiveRqDto);

    void deleteById(@NonNull Long id);

    boolean isOccupied(@NonNull Long id);
}
