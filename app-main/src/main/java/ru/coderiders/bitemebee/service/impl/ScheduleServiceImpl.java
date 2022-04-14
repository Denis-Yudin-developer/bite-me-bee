package ru.coderiders.bitemebee.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.bitemebee.entity.Activity;
import ru.coderiders.bitemebee.entity.BeeType;
import ru.coderiders.bitemebee.entity.Schedule;
import ru.coderiders.bitemebee.mapper.ScheduleMapper;
import ru.coderiders.bitemebee.repository.ScheduleRepository;
import ru.coderiders.bitemebee.rest.dto.ScheduleRqDto;
import ru.coderiders.bitemebee.rest.dto.ScheduleRsDto;
import ru.coderiders.bitemebee.service.ScheduleService;
import ru.coderiders.commons.rest.exception.BadRequestException;
import ru.coderiders.commons.rest.exception.NotFoundException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final String SCHEDULE_ALREADY_EXISTS = "Расписание для этой плановой работы уже существует";
    private final String SCHEDULE_ID_NOT_FOUND = "Расписание с id=%s не найдено";
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    @Override
    @Transactional
    public ScheduleRsDto create(@NonNull Long beeTypeId, @NonNull ScheduleRqDto scheduleRqDto) {
        log.debug("Создание расписания плановой работы, beeTypeId = {}, scheduleRqDto = {}", beeTypeId, scheduleRqDto);
        long activityId = scheduleRqDto.getActivityId();
        scheduleRepository.findByBeeType_IdAndActivity_Id(beeTypeId, activityId)
                .ifPresent(found -> {
                    throw new BadRequestException(SCHEDULE_ALREADY_EXISTS);
                });
        Activity activity = Activity.builder()
                .id(activityId)
                .build();
        BeeType beeType = BeeType.builder()
                .id(beeTypeId)
                .build();
        Schedule toCreate = scheduleMapper.toEntity(scheduleRqDto);
        toCreate.setBeeType(beeType);
        toCreate.setActivity(activity);
        Schedule created = scheduleRepository.save(toCreate);
        return scheduleMapper.toDto(created);
    }

    @Override
    @Transactional
    public Page<ScheduleRsDto> getAll(@NonNull Long beeTypeId, @NonNull Pageable pageable) {
        log.debug("Получение всех расписаний плановых работ, beeTypeId = {}, pageable = {}", beeTypeId, pageable);
        return scheduleRepository.findByBeeType_Id(beeTypeId, pageable)
                .map(scheduleMapper::toDto);
    }

    @Override
    @Transactional
    public ScheduleRsDto update(@NonNull Long scheduleId, @NonNull ScheduleRqDto scheduleRqDto) {
        log.debug("Обновление расписания плановой работы, scheduleId = {}, scheduleRqDto = {}", scheduleId, scheduleRqDto);
        long newInterval = scheduleRqDto.getIntervalInMinutes();
        return scheduleRepository.findById(scheduleId)
                .map(found -> {
                    found.setIntervalInMinutes(newInterval);
                    return found;
                })
                .map(scheduleMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(SCHEDULE_ID_NOT_FOUND, scheduleId)));
    }

    @Override
    @Transactional
    public void deleteById(@NonNull Long scheduleId) {
        log.debug("Удаление расписания плановой работы, scheduleId = {}", scheduleId);
        scheduleRepository.findById(scheduleId)
                .ifPresent(scheduleRepository::delete);
    }
}
