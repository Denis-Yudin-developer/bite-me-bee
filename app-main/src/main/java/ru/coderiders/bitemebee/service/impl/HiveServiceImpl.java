package ru.coderiders.bitemebee.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.bitemebee.entity.BeeFamily;
import ru.coderiders.bitemebee.entity.Hive;
import ru.coderiders.bitemebee.mapper.HiveMapper;
import ru.coderiders.bitemebee.repository.HiveRepository;
import ru.coderiders.bitemebee.rest.dto.HiveRqDto;
import ru.coderiders.bitemebee.rest.dto.HiveRsDto;
import ru.coderiders.bitemebee.service.HiveService;
import ru.coderiders.bitemebee.utils.BeanUtilsHelper;
import ru.coderiders.commons.rest.api.generator.HiveFeignApi;
import ru.coderiders.commons.rest.dto.GeneratorHiveRqDto;
import ru.coderiders.commons.rest.exception.BadRequestException;
import ru.coderiders.commons.rest.exception.NotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class HiveServiceImpl implements HiveService {
    private static final String[] IGNORED_ON_COPY_FIELDS = {"id"};
    private final String HIVE_NOT_FOUND = "Улей с id=%s не найден";
    private final String HIVE_ALREADY_EXISTS = "Улей с таким названием уже существует";
    private final HiveRepository hiveRepository;
    private final HiveMapper hiveMapper;
    private final HiveFeignApi hiveFeignApi;

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
        var hiveName = hiveRqDto.getName();
        hiveRepository.findByName(hiveName)
                .ifPresent(found -> {
                    throw new BadRequestException(HIVE_ALREADY_EXISTS);
                });
        Hive toCreate = hiveMapper.toEntity(hiveRqDto);
        Hive created = hiveRepository.save(toCreate);
        GeneratorHiveRqDto generatorHiveRqDto = GeneratorHiveRqDto.builder()
                .id(created.getId())
                .honeyCapacity(1.5 * created.getFrameCount())
                .build();
        hiveFeignApi.addHive(generatorHiveRqDto);
        return hiveMapper.toDto(created);
    }

    @Override
    @Transactional
    public HiveRsDto update(@NonNull Long id, @NonNull HiveRqDto hiveRqDto) {
        log.debug("Запрос на обновление улья по id = {}, HiveRqDto = {}", id, hiveRqDto);
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
    public void updateHoneyAmount(@NonNull Long id, @NonNull Double honeyIncrease) {
        log.debug("Запрос на обновление мёда по id = {}, honeyIncrease = {}", id, honeyIncrease);
        hiveRepository.findById(id)
                .map(found -> {
                    found.setHoneyAmount(found.getHoneyAmount() + honeyIncrease);
                    return found;
                })
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

    @Override
    @Transactional
    public boolean hiveExists(@NonNull Long id) {
        log.debug("Запрос на проверку существование улья по id = {}", id);
        return hiveRepository.existsById(id);
    }
}
