package com.example.BiteMeBee.mapper;

import com.example.BiteMeBee.entity.BeeType;
import com.example.BiteMeBee.rest.dto.BeeTypeRqDto;
import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
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
