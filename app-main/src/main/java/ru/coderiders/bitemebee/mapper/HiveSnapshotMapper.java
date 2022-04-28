package ru.coderiders.bitemebee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.HiveSnapshot;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class HiveSnapshotMapper {
    private final ModelMapper modelMapper;

    @PostConstruct
    private void init() {
        modelMapper.createTypeMap(HiveSnapshotDto.class, HiveSnapshot.class);
        modelMapper.createTypeMap(HiveSnapshot.class, HiveSnapshotDto.class)
                .addMappings(mapper -> mapper.map(src -> src.getHive().getId(), HiveSnapshotDto::setHiveId));
    }

    public HiveSnapshotDto toDto(HiveSnapshot hiveSnapshot) {
        return modelMapper.map(hiveSnapshot, HiveSnapshotDto.class);
    }

    public HiveSnapshot toEntity(HiveSnapshotDto hiveSnapshotDto) {
        return modelMapper.map(hiveSnapshotDto, HiveSnapshot.class);
    }
}
