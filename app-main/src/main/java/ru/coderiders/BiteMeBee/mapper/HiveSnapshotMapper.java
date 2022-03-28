package ru.coderiders.BiteMeBee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.BiteMeBee.entity.HiveSnapshot;
import ru.coderiders.Library.rest.dto.HiveSnapshotRqDto;
import ru.coderiders.Library.rest.dto.HiveSnapshotRsDto;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class HiveSnapshotMapper {

    private final ModelMapper modelMapper;

    @PostConstruct
    private void init() {
        modelMapper.createTypeMap(HiveSnapshotRqDto.class, HiveSnapshot.class);
        modelMapper.createTypeMap(HiveSnapshot.class, HiveSnapshotRsDto.class);
    }

    public HiveSnapshotRsDto toDto(HiveSnapshot hiveSnapshot) {
        return modelMapper.map(hiveSnapshot, HiveSnapshotRsDto.class);
    }
}
