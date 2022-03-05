package com.example.BiteMeBee.service.impl;

import com.example.BiteMeBee.entity.BeeType;
import com.example.BiteMeBee.mapper.BeeTypeMapper;
import com.example.BiteMeBee.repository.BeeTypeRepository;
import com.example.BiteMeBee.rest.dto.BeeTypeRqDto;
import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
import com.example.BiteMeBee.rest.exception.BadRequestException;
import com.example.BiteMeBee.rest.exception.NotFoundException;
import com.example.BiteMeBee.service.BeeTypeService;
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
public class BeeTypeServiceImpl implements BeeTypeService {

    private static final String[] IGNORED_ON_COPY_FIELDS = {"id"};

    private final String BEE_TYPE_NOT_FOUND = "Вид пчелы с id=%s не найден";
    private final String BEE_TYPE_ALREADY_EXISTS = "Вид пчелы с таким названием уже существует";

    private final BeeTypeRepository beeTypeRepository;
    private final BeeTypeMapper beeTypeMapper;

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
        log.debug("Запрос на получение вида пчелы по id = {}", id);

        return beeTypeRepository.findById(id)
                .map(beeTypeMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_TYPE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public BeeTypeRsDto create(@NonNull BeeTypeRqDto beeTypeRqDto) {
        log.debug("Запрос на создание нового вида пчёл, BeeTypeRqDto = {}", beeTypeRqDto);

        beeTypeRepository.findByTitle(beeTypeRqDto.getTitle())
                .ifPresent(meterType -> {
                    throw new BadRequestException(BEE_TYPE_ALREADY_EXISTS);
                });

        BeeType beeTypeSrc = beeTypeMapper.toEntity(beeTypeRqDto);
        BeeType beeTypeDst = beeTypeRepository.save(beeTypeSrc);
        return beeTypeMapper.toDto(beeTypeDst);
    }

    @Override
    public BeeTypeRsDto update(@NonNull Long id, @NonNull BeeTypeRqDto beeTypeRqDto) {
        log.debug("Запрос на обновление вида пчелы, BeeTypeRqDto = {}", beeTypeRqDto);

        return beeTypeRepository.findById(id)
                .map(src -> {
                    var newBeeType = beeTypeMapper.toEntity(beeTypeRqDto);
                    BeanUtils.copyProperties(newBeeType, src,
                            BeanUtilsHelper.getNullPropertyNames(newBeeType, IGNORED_ON_COPY_FIELDS));
                    return src;
                })
                .map(beeTypeMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_TYPE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public void deleteById(@NonNull Long id) {
        log.debug("Запрос на удаление вида пчелы по id = {}", id);

        beeTypeRepository.findById(id)
                .ifPresentOrElse(beeTypeRepository::delete,
                        () -> {
                            throw new NotFoundException(String.format(BEE_TYPE_NOT_FOUND, id));
                        });
    }
}
