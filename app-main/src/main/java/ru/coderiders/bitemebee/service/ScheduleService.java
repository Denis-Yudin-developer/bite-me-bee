package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coderiders.bitemebee.rest.dto.ScheduleRqDto;
import ru.coderiders.bitemebee.rest.dto.ScheduleRsDto;

public interface ScheduleService {

    ScheduleRsDto create(@NonNull Long beeTypeId, @NonNull ScheduleRqDto scheduleRqDto);

    Page<ScheduleRsDto> getAll(@NonNull Long beeTypeId, @NonNull Pageable pageable);

    ScheduleRsDto update(@NonNull Long scheduleId, @NonNull ScheduleRqDto scheduleRqDto);

    void deleteById(@NonNull Long scheduleId);
}
