package ru.coderiders.Main.service.impl;

import ru.coderiders.Main.entity.BeeFamily;
import ru.coderiders.Main.entity.Hive;
import ru.coderiders.Main.mapper.HiveMapper;
import ru.coderiders.Main.repository.HiveRepository;
import ru.coderiders.Main.rest.dto.HiveRqDto;
import ru.coderiders.Main.rest.dto.HiveRsDto;
import ru.coderiders.Main.rest.exception.NotFoundException;
import ru.coderiders.Main.service.HiveService;
import ru.coderiders.Main.utils.BeanUtilsHelper;
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
public class HiveServiceImpl implements HiveService {

    private static final String[] IGNORED_ON_COPY_FIELDS = {"id"};

    private final String HIVE_NOT_FOUND = "Улей с id=%s не найден";

    private final HiveRepository hiveRepository;
    private final HiveMapper hiveMapper;

    @Override
    @Transactional
    public Page<HiveRsDto> getAll(@NonNull Pageable pageable) {
        log.debug("Запрос на получение всех ульев, pageable = {}", pageable);

        return hiveRepository.findAll(pageable)
                .map(hiveMapper::toDto);
    }

    @Override
    @Transactional
    public HiveRsDto getById(@NonNull Long id) {
        log.debug("Запрос на получение улья по id = {}", id);

        return hiveRepository.findById(id)
                .map(hiveMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(HIVE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public HiveRsDto create(@NonNull HiveRqDto hiveRqDto) {
        log.debug("Запрос на создание нового улья, HiveRqDto = {}", hiveRqDto);

        Hive toCreate = hiveMapper.toEntity(hiveRqDto);
        Hive created = hiveRepository.save(toCreate);
        return hiveMapper.toDto(created);
    }

    @Override
    @Transactional
    public HiveRsDto update(@NonNull Long id, @NonNull HiveRqDto hiveRqDto) {
        log.debug("Запрос на обновление улья, HiveRqDto = {}", hiveRqDto);

        return hiveRepository.findById(id)
                .map(found -> {
                    var update = hiveMapper.toEntity(hiveRqDto);
                    BeanUtils.copyProperties(update, found,
                            BeanUtilsHelper.getNullPropertyNames(update, IGNORED_ON_COPY_FIELDS));
                    return found;
                })
                .map(hiveMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(HIVE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public void deleteById(@NonNull Long id) {
        log.debug("Запрос на удаление улья по id = {}", id);

        hiveRepository.findById(id)
                .ifPresent(hiveRepository::delete);
    }

    @Override
    @Transactional
    public boolean isOccupied(@NonNull Long id) {
        log.debug("Проверяем, является ли улей занятым живой пчелиной семьёй, id = {}", id);

        return hiveRepository.findById(id)
                .map(hive -> hive.getBeeFamilies().stream().anyMatch(BeeFamily::getIsAlive))
                .orElseThrow(() -> new NotFoundException(String.format(HIVE_NOT_FOUND, id)));
    }
}
