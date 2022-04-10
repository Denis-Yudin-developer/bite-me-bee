package ru.coderiders.bitemebee.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.bitemebee.entity.BeeType;
import ru.coderiders.bitemebee.mapper.BeeTypeMapper;
import ru.coderiders.bitemebee.repository.BeeTypeRepository;
import ru.coderiders.bitemebee.rest.dto.BeeTypeRqDto;
import ru.coderiders.bitemebee.rest.dto.BeeTypeRsDto;
import ru.coderiders.bitemebee.rest.dto.ScheduleRqDto;
import ru.coderiders.bitemebee.rest.dto.ScheduleRsDto;
import ru.coderiders.bitemebee.service.BeeTypeService;
import ru.coderiders.bitemebee.service.ScheduleService;
import ru.coderiders.bitemebee.utils.BeanUtilsHelper;
import ru.coderiders.commons.rest.exception.BadRequestException;
import ru.coderiders.commons.rest.exception.NotFoundException;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class BeeTypeServiceImpl implements BeeTypeService {
    private static final String[] IGNORED_ON_COPY_FIELDS = {"id"};
    private final String BEE_TYPE_NOT_FOUND = "Вид пчёл с id=%s не найден";
    private final String BEE_TYPE_ALREADY_EXISTS = "Вид пчёл с таким названием уже существует";
    private final BeeTypeRepository beeTypeRepository;
    private final BeeTypeMapper beeTypeMapper;
    private final ScheduleService scheduleService;

    @Override
    @Transactional
    public Page<BeeTypeRsDto> getAll(@NonNull Pageable pageable) {
        log.debug("Запрос на получение всех видов пчёл, pageable = {}", pageable);
        return beeTypeRepository.findAll(pageable)
                .map(beeTypeMapper::toDto);
    }

    @Override
    @Transactional
    public BeeTypeRsDto getById(@NonNull Long id) {
        log.debug("Запрос на получение вида пчёл по id = {}", id);
        return beeTypeRepository.findById(id)
                .map(beeTypeMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_TYPE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public BeeTypeRsDto create(@NonNull BeeTypeRqDto beeTypeRqDto) {
        log.debug("Запрос на создание нового вида пчёл, BeeTypeRqDto = {}", beeTypeRqDto);
        var typeTitle = beeTypeRqDto.getTitle();
        beeTypeRepository.findByTitle(typeTitle)
                .ifPresent(meterType -> {
                    throw new BadRequestException(BEE_TYPE_ALREADY_EXISTS);
                });
        BeeType toCreate = beeTypeMapper.toEntity(beeTypeRqDto);
        BeeType created = beeTypeRepository.save(toCreate);
        BeeTypeRsDto beeTypeRsDto = beeTypeMapper.toDto(created);
        List<ScheduleRsDto> schedules = beeTypeRqDto.getSchedules().stream()
                .map(schedule -> {
                    ScheduleRqDto scheduleToCreate = ScheduleRqDto.builder()
                            .activityId(schedule.getActivityId())
                            .intervalInMinutes(schedule.getIntervalInMinutes())
                            .build();
                    return scheduleService.create(toCreate.getId(), scheduleToCreate);
                }).toList();
        beeTypeRsDto.setSchedules(schedules);
        return beeTypeRsDto;
    }

    @Override
    public BeeTypeRsDto update(@NonNull Long id, @NonNull BeeTypeRqDto beeTypeRqDto) {
        log.debug("Запрос на обновление вида пчёл по id = {}, BeeTypeRqDto = {}", id, beeTypeRqDto);
        return beeTypeRepository.findById(id)
                .map(found -> {
                    BeeType update = beeTypeMapper.toEntity(beeTypeRqDto);
                    BeanUtils.copyProperties(update, found,
                            BeanUtilsHelper.getNullPropertyNames(update, IGNORED_ON_COPY_FIELDS));
                    return found;
                })
                .map(beeTypeMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_TYPE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public void deleteById(@NonNull Long id) {
        log.debug("Запрос на удаление вида пчёл по id = {}", id);
        beeTypeRepository.findById(id)
                .ifPresent(beeTypeRepository::delete);
    }
}
