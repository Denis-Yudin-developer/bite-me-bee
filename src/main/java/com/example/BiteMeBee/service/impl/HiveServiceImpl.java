package com.example.BiteMeBee.service.impl;

import com.example.BiteMeBee.entity.Hive;
import com.example.BiteMeBee.mapper.HiveMapper;
import com.example.BiteMeBee.repository.HiveRepository;
import com.example.BiteMeBee.rest.dto.HiveRqDto;
import com.example.BiteMeBee.rest.dto.HiveRsDto;
import com.example.BiteMeBee.rest.exception.NotFoundException;
import com.example.BiteMeBee.service.HiveService;
import com.example.BiteMeBee.utils.BeanUtilsHelper;
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

        Hive hiveSrc = hiveMapper.toEntity(hiveRqDto);
        Hive hiveDst = hiveRepository.save(hiveSrc);
        return hiveMapper.toDto(hiveDst);
    }

    @Override
    @Transactional
    public HiveRsDto update(@NonNull Long id, @NonNull HiveRqDto hiveRqDto) {
        log.debug("Запрос на обновление улья, HiveRqDto = {}", hiveRqDto);

        return hiveRepository.findById(id)
                .map(src -> {
                    var newHive = hiveMapper.toEntity(hiveRqDto);
                    BeanUtils.copyProperties(newHive, src,
                            BeanUtilsHelper.getNullPropertyNames(newHive, IGNORED_ON_COPY_FIELDS));
                    return src;
                })
                .map(hiveMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(HIVE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public void deleteById(@NonNull Long id) {
        log.debug("Запрос на удаление улья по id = {}", id);

        hiveRepository.findById(id)
                .ifPresentOrElse(hiveRepository::delete,
                        () -> {
                            throw new NotFoundException(String.format(HIVE_NOT_FOUND, id));
                        });
    }
}
