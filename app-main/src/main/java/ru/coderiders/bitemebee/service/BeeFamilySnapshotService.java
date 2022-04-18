package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coderiders.bitemebee.entity.BeeFamilySnapshot;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotRqDto;

public interface BeeFamilySnapshotService {
    Page<BeeFamilySnapshotDto> getSnapshots(@NonNull Pageable pageable, @NonNull BeeFamilySnapshotRqDto beeFamilySnapshotRqDto);

    BeeFamilySnapshot createSnapshot(@NonNull BeeFamilySnapshotDto beeFamilySnapshotGeneratorDto);
}
