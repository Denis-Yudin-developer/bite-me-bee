package ru.coderiders.BiteMeBee.service.impl;

import ru.coderiders.BiteMeBee.entity.Activity;
import ru.coderiders.BiteMeBee.mapper.ActivityMapper;
import ru.coderiders.BiteMeBee.repository.ActivityRepository;
import ru.coderiders.BiteMeBee.rest.dto.ActivityRqDto;
import ru.coderiders.BiteMeBee.rest.dto.ActivityRsDto;
import ru.coderiders.BiteMeBee.rest.exception.BadRequestException;
import ru.coderiders.BiteMeBee.rest.exception.NotFoundException;
import ru.coderiders.BiteMeBee.service.ActivityService;
import ru.coderiders.BiteMeBee.utils.BeanUtilsHelper;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private static final String[] IGNORED_ON_COPY_FIELDS = {"id"};

    private final String ACTIVITY_TYPE_NOT_FOUND = "Тип работы с id=%s не найден";
    private final String ACTIVITY_TYPE_ALREADY_EXISTS = "Тип работы с таким названием уже существует";

    private final ActivityRepository activityRepository;
    private final ActivityMapper activityMapper;

    @Override
    @Transactional
    public Page<ActivityRsDto> getAll(@NonNull Pageable pageable) {
        log.debug("Запрос на получение всех типовых работ, pageable = {}", pageable);

        return activityRepository.findAll(pageable)
                .map(activityMapper::toDto);
    }

    @Override
    @Transactional
    public ActivityRsDto getById(@NonNull Long id) {
        log.debug("Запрос на получение типовой работы по id = {}", id);

        return activityRepository.findById(id)
                .map(activityMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(ACTIVITY_TYPE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public ActivityRsDto create(@NonNull ActivityRqDto activityRqDto) {
        log.debug("Запрос на создание новой типовой работы, ActivityRqDto = {}", activityRqDto);

        activityRepository.findByTitle(activityRqDto.getTitle())
                .ifPresent(meterType -> {
                    throw new BadRequestException(ACTIVITY_TYPE_ALREADY_EXISTS);
                });

        Activity activitySrc = activityMapper.toEntity(activityRqDto);
        Activity activityDst = activityRepository.save(activitySrc);
        return activityMapper.toDto(activityDst);
    }

    @Override
    @Transactional
    public ActivityRsDto update(@NonNull Long id, @NonNull ActivityRqDto activityRqDto) {
        log.debug("Запрос на обновление типовой работы, ActivityRqDto = {}", activityRqDto);

        return activityRepository.findById(id)
                .map(src -> {
                    var newActivity = activityMapper.toEntity(activityRqDto);
                    BeanUtils.copyProperties(newActivity, src,
                            BeanUtilsHelper.getNullPropertyNames(newActivity, IGNORED_ON_COPY_FIELDS));
                    return src;
                })
                .map(activityMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(ACTIVITY_TYPE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public void deleteById(@NonNull Long id) {
        log.debug("Запрос на удаление типовой работы по id = {}", id);

        activityRepository.findById(id)
                .ifPresentOrElse(activityRepository::delete,
                        () -> {
                            throw new NotFoundException(String.format(ACTIVITY_TYPE_NOT_FOUND, id));
                        });
    }
}
