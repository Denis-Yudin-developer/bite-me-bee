package com.example.BiteMeBee.mapper;

import com.example.BiteMeBee.entity.Hive;
import com.example.BiteMeBee.rest.dto.BeeFamilyRsDto;
import com.example.BiteMeBee.rest.dto.HiveRqDto;
import com.example.BiteMeBee.rest.dto.HiveRsDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HiveMapper {

    private final ModelMapper modelMapper;
    private final BeeFamilyMapper beeFamilyMapper;

    @PostConstruct
    private void init() {
        modelMapper.createTypeMap(Hive.class, HiveRsDto.class)
                .addMappings(mapping -> mapping.skip(HiveRsDto::setBeeFamilies))
                .setPostConverter(toDtoPostConverter());
    }

    public Hive toEntity(HiveRqDto hiveRqDto) {
        return modelMapper.map(hiveRqDto, Hive.class);
    }

    public HiveRsDto toDto(Hive hive) {
        return modelMapper.map(hive, HiveRsDto.class);
    }

    Converter<Hive, HiveRsDto> toDtoPostConverter() {
        return context -> {
            var hiveSrc = context.getSource();
            var hiveDst = context.getDestination();

            if (hiveSrc.getBeeFamilies() == null || hiveSrc.getBeeFamilies().isEmpty()) {
                List<BeeFamilyRsDto> emptyList = Collections.emptyList();
                hiveDst.setBeeFamilies(emptyList);
            } else {
                List<BeeFamilyRsDto> familyList = hiveSrc.getBeeFamilies()
                        .stream()
                        .map(beeFamilyMapper::toDto)
                        .collect(Collectors.toList());
                hiveDst.setBeeFamilies(familyList);
            }

            return hiveDst;
        };
    }
}
