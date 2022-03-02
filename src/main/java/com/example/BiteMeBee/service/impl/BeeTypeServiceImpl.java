package com.example.BiteMeBee.service.impl;

import com.example.BiteMeBee.entity.BeeType;
import com.example.BiteMeBee.mapper.BeeTypeMapper;
import com.example.BiteMeBee.repository.BeeTypeRepository;
import com.example.BiteMeBee.rest.dto.BeeTypeRqDto;
import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
import com.example.BiteMeBee.rest.exception.BadRequestException;
import com.example.BiteMeBee.rest.exception.NotFoundException;
import com.example.BiteMeBee.service.BeeTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeeTypeServiceImpl implements BeeTypeService {

    private final String BEE_TYPE_NOT_FOUND = "Вид пчелы с id=%s не найден";
    private final String BEE_TYPE_ALREADY_EXISTS = "Вид пчелы с таким названием уже существует";

    private final BeeTypeRepository beeTypeRepository;
    private final BeeTypeMapper beeTypeMapper;

    @Override
    @Transactional
    public Page<BeeTypeRsDto> getAll(Pageable pageable) {
        log.debug("Запрос на получение всех видов пчёл, pageable = {}", pageable);

        return beeTypeRepository.findAll(pageable)
                .map(beeTypeMapper::toDto);
    }

    @Override
    @Transactional
    public BeeTypeRsDto getById(Long id) {
        log.debug("Запрос на получение вида пчелы по id = {}", id);

        return beeTypeRepository.findById(id)
                .map(beeTypeMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_TYPE_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public BeeTypeRsDto create(BeeTypeRqDto beeTypeRqDto) {
        log.debug("Запрос на создание нового вида пчёл, BeeTypeRqDto = {}", beeTypeRqDto);

        beeTypeRepository.getBeeTypeByTitle(beeTypeRqDto.getTitle())
                .ifPresent(meterType -> {
                    throw new BadRequestException(BEE_TYPE_ALREADY_EXISTS);
                });

        BeeType beeTypeSrc = beeTypeMapper.toEntity(beeTypeRqDto);
        BeeType beeTypeDst = beeTypeRepository.save(beeTypeSrc);
        return beeTypeMapper.toDto(beeTypeDst);
    }

    @Override
    public BeeTypeRsDto update(BeeTypeRqDto beeTypeRqDto, Long id) {
        log.debug("Запрос на обновление вида пчелы по id = {}", id);

        Optional<BeeType> beeTypeOptional = beeTypeRepository.findById(id);

        if(beeTypeOptional.isPresent()) {
            BeeType beeTypeDst = beeTypeOptional.get();
            beeTypeDst.setTitle(beeTypeRqDto.getTitle());
            beeTypeDst.setDescription(beeTypeRqDto.getDescription());
            beeTypeDst.setMinCo2(beeTypeRqDto.getMinCo2());
            beeTypeDst.setMaxCo2(beeTypeRqDto.getMaxCo2());
            beeTypeDst.setMinHumidity(beeTypeRqDto.getMinHumidity());
            beeTypeDst.setMaxHumidity(beeTypeRqDto.getMaxHumidity());
            beeTypeDst.setMinTemperature(beeTypeRqDto.getMinTemperature());
            beeTypeDst.setMaxTemperature(beeTypeRqDto.getMaxTemperature());
            beeTypeDst.setColdResistance(beeTypeRqDto.getColdResistance());
            beeTypeDst.setDiseaseResistance(beeTypeRqDto.getDiseaseResistance());
            beeTypeDst.setHoneyProductivity(beeTypeRqDto.getHoneyProductivity());
            beeTypeDst.setEggProductivity(beeTypeRqDto.getEggProductivity());
            beeTypeDst.setAggressionLevel(beeTypeRqDto.getAggressionLevel());
            beeTypeDst.setRoilingLevel(beeTypeRqDto.getRoilingLevel());

            return beeTypeMapper.toDto(beeTypeDst);

        } else throw new NotFoundException(String.format(BEE_TYPE_NOT_FOUND, id));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        log.debug("Запрос на удаление вида пчелы по id = {}", id);

        beeTypeRepository.findById(id)
                .ifPresentOrElse(beeTypeRepository::delete,
                        () -> {
                            throw new NotFoundException(String.format(BEE_TYPE_NOT_FOUND, id));
                        });
    }
}
