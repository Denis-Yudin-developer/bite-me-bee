package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coderiders.bitemebee.rest.dto.HiveRqDto;
import ru.coderiders.bitemebee.rest.dto.HiveRsDto;

public interface HiveService {
    Page<HiveRsDto> getAll(@NonNull Pageable pageable);

    HiveRsDto getById(@NonNull Long id);

    HiveRsDto create(@NonNull HiveRqDto hiveRqDto);

    HiveRsDto update(@NonNull Long id, @NonNull HiveRqDto hiveRqDto);

    void updateHoney(@NonNull Long id, @NonNull Double honeyIncrease);

    void deleteById(@NonNull Long id);

    boolean isOccupied(@NonNull Long id);

    boolean isExists(@NonNull Long id);
}
