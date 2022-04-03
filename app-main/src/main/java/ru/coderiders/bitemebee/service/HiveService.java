package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coderiders.bitemebee.entity.Hive;
import ru.coderiders.bitemebee.rest.dto.HiveRqDto;
import ru.coderiders.bitemebee.rest.dto.HiveRsDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotGeneratorDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotRqDto;

import java.util.List;

public interface HiveService {
    Page<HiveRsDto> getAll(@NonNull Pageable pageable);

    Hive getEntityById(@NonNull Long id);

    HiveRsDto getById(@NonNull Long id);

    HiveRsDto create(@NonNull HiveRqDto hiveRqDto);

    HiveRsDto update(@NonNull Long id, @NonNull HiveRqDto hiveRqDto);

    void updateHoneyAmount(@NonNull Long id, @NonNull Double honeyIncrease);

    void deleteById(@NonNull Long id);

    boolean isOccupied(@NonNull Long id);

    boolean hiveExists(@NonNull Long id);
}
