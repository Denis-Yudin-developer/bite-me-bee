package ru.coderiders.bitemebee.data;

import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotRqDto;

import java.time.Instant;

public class BeeFamilySnapshotData {
    public static final BeeFamilySnapshotRqDto BEE_FAMILY_SNAPSHOT_RQ_DTO_1 = BeeFamilySnapshotRqDto.builder()
            .familyId(1L)
            .dateFrom(Instant.parse("2022-03-14T18:30:50.630Z"))
            .dateTo(Instant.parse("2022-03-29T15:30:04.340Z"))
            .build();

    public static final BeeFamilySnapshotDto BEE_FAMILY_SNAPSHOT_DTO_1 = BeeFamilySnapshotDto.builder()
            .familyId(1L)
            .createdAt("2022-03-14T18:30:50.630Z")
            .populationIncrease(5L)
            .dronePopulationIncrease(1L)
            .workerPopulationIncrease(4L)
            .queenPopulationIncrease(0L)
            .activity(1.0)
            .mood(1.0)
            .build();

    public static final BeeFamilySnapshotDto BEE_FAMILY_SNAPSHOT_DTO_2 = BeeFamilySnapshotDto.builder()
            .familyId(2L)
            .createdAt("2022-03-16T18:12:23.635Z")
            .populationIncrease(7L)
            .dronePopulationIncrease(2L)
            .workerPopulationIncrease(5L)
            .queenPopulationIncrease(0L)
            .activity(1.1)
            .mood(1.1)
            .build();
}
