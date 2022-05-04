package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coderiders.bitemebee.rest.dto.JobNoteRqDto;
import ru.coderiders.bitemebee.rest.dto.JobRqDto;
import ru.coderiders.bitemebee.rest.dto.JobRsDto;

public interface JobService {
    Page<JobRsDto> getAll(@NonNull Pageable pageable);

    JobRsDto getById(@NonNull Long id);

    JobRsDto create(@NonNull JobRqDto jobRqDto);

    JobRsDto update(@NonNull Long id, @NonNull JobNoteRqDto jobNoteRqDto);

    void complete(@NonNull Long id);

    void verifyPlannedJobs();
}
