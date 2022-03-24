package ru.coderiders.BiteMeBee.service.impl;

import ru.coderiders.BiteMeBee.entity.BeeType;
import ru.coderiders.BiteMeBee.mapper.BeeTypeMapper;
import ru.coderiders.BiteMeBee.repository.BeeTypeRepository;
import ru.coderiders.BiteMeBee.rest.dto.BeeTypeRqDto;
import ru.coderiders.BiteMeBee.rest.dto.BeeTypeRsDto;
import ru.coderiders.BiteMeBee.rest.exception.BadRequestException;
import ru.coderiders.BiteMeBee.rest.exception.NotFoundException;
import ru.coderiders.BiteMeBee.service.BeeTypeService;
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
public class BeeTypeServiceImpl implements BeeTypeService {

    private static final String[] IGNORED_ON_COPY_FIELDS = {"id"};

    private final String BEE_TYPE_NOT_FOUND = "Вид пчелы с id=%s не найден";
    private final String BEE_TYPE_ALREADY_EXISTS = "Вид пчёл с названием «%s» уже существует";

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
                    throw new BadRequestException(String.format(BEE_TYPE_ALREADY_EXISTS, typeTitle));
                });

        BeeType toCreate = beeTypeMapper.toEntity(beeTypeRqDto);
        BeeType created = beeTypeRepository.save(toCreate);
        return beeTypeMapper.toDto(created);
    }

    @Override
    public BeeTypeRsDto update(@NonNull Long id, @NonNull BeeTypeRqDto beeTypeRqDto) {
        log.debug("Запрос на обновление вида пчёл, BeeTypeRqDto = {}", beeTypeRqDto);

        return beeTypeRepository.findById(id)
                .map(found -> {
                    var update = beeTypeMapper.toEntity(beeTypeRqDto);
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
