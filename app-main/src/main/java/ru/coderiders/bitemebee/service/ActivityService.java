package ru.coderiders.bitemebee.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import ru.coderiders.bitemebee.rest.dto.ActivityRqDto;
import ru.coderiders.bitemebee.rest.dto.ActivityRsDto;

public interface ActivityService {
    Page<ActivityRsDto> getAll(@NonNull Pageable pageable);

    ActivityRsDto getById(@NonNull Long id);

    ActivityRsDto create(@NonNull ActivityRqDto activityRqDto);

    ActivityRsDto update(@NonNull Long id, @NonNull ActivityRqDto activityRqDto);

    void deleteById(@NonNull Long id);
}
