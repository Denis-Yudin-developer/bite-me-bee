package com.example.BiteMeBee.mapper;

import com.example.BiteMeBee.entity.Hive;
import com.example.BiteMeBee.rest.dto.HiveRqDto;
import com.example.BiteMeBee.rest.dto.HiveRsDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HiveMapper {

    private final ModelMapper modelMapper;

    public Hive toEntity(HiveRqDto hiveRqDto) {
        return modelMapper.map(hiveRqDto, Hive.class);
    }

    public HiveRsDto toDto(Hive hive) {
        return modelMapper.map(hive, HiveRsDto.class);
    }
}
