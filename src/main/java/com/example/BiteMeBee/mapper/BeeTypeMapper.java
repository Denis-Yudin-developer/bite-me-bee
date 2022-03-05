package com.example.BiteMeBee.mapper;

import com.example.BiteMeBee.entity.BeeType;
import com.example.BiteMeBee.rest.dto.BeeTypeRqDto;
import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
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