package ru.coderiders.bitemebee.rest.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.coderiders.bitemebee.rest.api.JobAPI;
import ru.coderiders.bitemebee.rest.dto.JobNoteRqDto;
import ru.coderiders.bitemebee.rest.dto.JobRqDto;
import ru.coderiders.bitemebee.rest.dto.JobRsDto;
import ru.coderiders.bitemebee.service.JobService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JobController implements JobAPI {
    private final JobService jobService;

    @Override
    public ResponseEntity<JobRsDto> create(JobRqDto jobRqDto) {
        JobRsDto created = jobService.create(jobRqDto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @Override
    public JobRsDto getById(Long id) {
        return jobService.getById(id);
    }

    @Override
    public Page<JobRsDto> getAll(Pageable pageable) {
        return jobService.getAll(pageable);
    }

    @Override
    public JobRsDto update(Long id, JobNoteRqDto jobNoteRqDto) {
        return jobService.update(id, jobNoteRqDto);
    }

    @Override
    public ResponseEntity<Void> complete(Long id) {
        jobService.complete(id);
        return ResponseEntity.accepted().build();
    }
}
