package ru.coderiders.bitemebee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.BeeType;
import ru.coderiders.bitemebee.rest.dto.BeeTypeRqDto;
import ru.coderiders.bitemebee.rest.dto.BeeTypeRsDto;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class BeeTypeMapper {
    private final ModelMapper modelMapper;

    @PostConstruct
    private void init() {
        modelMapper.createTypeMap(BeeTypeRqDto.class, BeeType.class);
        modelMapper.createTypeMap(BeeType.class, BeeTypeRsDto.class);
    }

    public BeeType toEntity(BeeTypeRqDto beeTypeRqDto) {
        return modelMapper.map(beeTypeRqDto, BeeType.class);
    }

    public BeeTypeRsDto toDto(BeeType beeType) {
        return modelMapper.map(beeType, BeeTypeRsDto.class);
    }
}
