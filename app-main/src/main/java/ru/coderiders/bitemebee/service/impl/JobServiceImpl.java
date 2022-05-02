package ru.coderiders.bitemebee.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import ru.coderiders.bitemebee.entity.Job;
import ru.coderiders.bitemebee.mapper.JobMapper;
import ru.coderiders.bitemebee.repository.JobRepository;
import ru.coderiders.bitemebee.rest.dto.JobNoteRqDto;
import ru.coderiders.bitemebee.rest.dto.JobRqDto;
import ru.coderiders.bitemebee.rest.dto.JobRsDto;
import ru.coderiders.bitemebee.service.ActivityService;
import ru.coderiders.bitemebee.service.HiveService;
import ru.coderiders.bitemebee.service.JobService;
import ru.coderiders.bitemebee.service.UserService;
import ru.coderiders.commons.rest.exception.BadRequestException;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {
    private final String JOB_NOT_FOUND = "Работа с id=%s не найдена";
    private final String JOB_ALREADY_EXIST = "Для данного улья данная работа уже существует";
    private final JobRepository jobRepository;
    private final JobMapper jobMapper;
    private final ActivityService activityService;
    private final HiveService hiveService;
    private final UserService userService;

    @Override
    @Transactional
    public Page<JobRsDto> getAll(@NonNull Pageable pageable) {
        log.debug("Запрос на получение всех работ, pageable = {}", pageable);
        return jobRepository.findAll(pageable)
                .map(jobMapper::toDto);
    }

    @Override
    @Transactional
    public JobRsDto getById(@NonNull Long id) {
        log.debug("Запрос на получение работы по id = {}", id);
        return jobRepository.findById(id)
                .map(jobMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(JOB_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public JobRsDto create(@NonNull JobRqDto jobRqDto) {
        log.debug("Запрос на создание новой работы, jobRqDto = {}", jobRqDto);
        var hiveId = jobRqDto.getHiveId();
        var activityId = jobRqDto.getActivityId();
        var userId = jobRqDto.getUserId();
        try {
            hiveService.getById(hiveId);
            activityService.getById(activityId);
            userService.getById(userId);
        } catch (NotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }
        if (!jobRepository.findByCompletedJobs(hiveId, activityId).isEmpty()){
            throw new BadRequestException(JOB_ALREADY_EXIST);
        }
        Job toCreate = jobMapper.toEntity(jobRqDto);
        Job created = jobRepository.save(toCreate);
        log.debug("Работа для улья создана, id = {}", hiveId);
        return jobMapper.toDto(created);
    }

    @Override
    @Transactional
    public JobRsDto update(@NonNull Long id, @NonNull JobNoteRqDto jobNoteRqDto) {
        log.debug("Запрос на обновление работы по id = {}, jobNoteRqDto = {}", id, jobNoteRqDto);
        return jobRepository.findById(id)
                .map(found -> {
                    found.setNote(jobNoteRqDto.getNote());
                    return found;
                })
                .map(jobMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(JOB_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public void complete(@NonNull Long id) {
        log.debug("Запрос на завершение работы по id = {}", id);
        jobRepository.findById(id).
                ifPresentOrElse(found -> {
                            found.setIsCompleted(true);
                            found.setClosedAt(Instant.now());
                        },
                        () -> {
                            throw new NotFoundException(String.format(JOB_NOT_FOUND, id));
                        });
    }
}
