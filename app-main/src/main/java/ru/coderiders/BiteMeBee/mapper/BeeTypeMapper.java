package ru.coderiders.BiteMeBee.mapper;

import ru.coderiders.BiteMeBee.entity.BeeType;
import ru.coderiders.BiteMeBee.rest.dto.BeeTypeRqDto;
import ru.coderiders.BiteMeBee.rest.dto.BeeTypeRsDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

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
