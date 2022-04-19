package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import ru.coderiders.bitemebee.entity.BeeFamilySnapshot;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotRqDto;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;

import java.util.List;

public interface BeeFamilySnapshotService {
    List<BeeFamilySnapshotDto> getSnapshots(@NonNull BeeFamilySnapshotRqDto beeFamilySnapshotRqDto);

    BeeFamilySnapshot createSnapshot(@NonNull BeeFamilySnapshotDto beeFamilySnapshotGeneratorDto);
}
