package ru.coderiders.Main.mapper;

import ru.coderiders.Main.entity.BeeType;
import ru.coderiders.Main.rest.dto.BeeTypeRqDto;
import ru.coderiders.Main.rest.dto.BeeTypeRsDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BeeTypeMapper {

    private final ModelMapper modelMapper;

    public BeeType toEntity(BeeTypeRqDto beeTypeRqDto) {
        return modelMapper.map(beeTypeRqDto, BeeType.class);
    }

    public BeeTypeRsDto toDto(BeeType beeType) {
        return modelMapper.map(beeType, BeeTypeRsDto.class);
    }
}
