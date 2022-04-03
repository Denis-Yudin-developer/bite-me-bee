package ru.coderiders.bitemebee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.HiveSnapshot;
import ru.coderiders.commons.rest.dto.HiveSnapshotGeneratorDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotRqDto;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class HiveSnapshotMapper {
    private final ModelMapper modelMapper;

    @PostConstruct
    private void init() {
        modelMapper.createTypeMap(HiveSnapshotRqDto.class, HiveSnapshot.class);
        modelMapper.createTypeMap(HiveSnapshot.class, HiveSnapshotGeneratorDto.class);
    }

    public HiveSnapshotGeneratorDto toDto(HiveSnapshot hiveSnapshot) {
        return modelMapper.map(hiveSnapshot, HiveSnapshotGeneratorDto.class);
    }

    public HiveSnapshot toEntity(HiveSnapshotGeneratorDto hiveSnapshotGeneratorDto) {
        return modelMapper.map(hiveSnapshotGeneratorDto, HiveSnapshot.class);
    }
}
