package ru.coderiders.bitemebee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.BeeFamilySnapshot;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class BeeFamilySnapshotMapper {
    private final ModelMapper modelMapper;

    @PostConstruct
    private void init() {
        modelMapper.createTypeMap(BeeFamilySnapshotDto.class, BeeFamilySnapshot.class);
        modelMapper.createTypeMap(BeeFamilySnapshot.class, BeeFamilySnapshotDto.class)
                .addMappings(mapper -> mapper.map(src -> src.getBeeFamily().getId(), BeeFamilySnapshotDto::setFamilyId));
    }

    public BeeFamilySnapshotDto toDto(BeeFamilySnapshot beeFamilySnapshot) {
        return modelMapper.map(beeFamilySnapshot, BeeFamilySnapshotDto.class);
    }

    public BeeFamilySnapshot toEntity(BeeFamilySnapshotDto beeFamilySnapshotDto){
        return modelMapper.map(beeFamilySnapshotDto, BeeFamilySnapshot.class);
    }
}
