package ru.coderiders.Main.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.Main.entity.HiveSnapshot;
import ru.coderiders.Main.rest.dto.HiveSnapshotRsDto;

@Component
@RequiredArgsConstructor
public class HiveSnapshotMapper {

    private final ModelMapper modelMapper;

    public HiveSnapshotRsDto toDto(HiveSnapshot hiveSnapshot) {
        return modelMapper.map(hiveSnapshot, HiveSnapshotRsDto.class);
    }
}
