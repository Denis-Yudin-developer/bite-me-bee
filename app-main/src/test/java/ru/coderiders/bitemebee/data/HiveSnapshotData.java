package ru.coderiders.bitemebee.data;

import ru.coderiders.commons.rest.dto.HiveSnapshotDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotRqDto;

import java.time.Instant;

public class HiveSnapshotData {
    public static final HiveSnapshotRqDto HIVE_SNAPSHOT_RQ_DTO_1 = HiveSnapshotRqDto.builder()
            .hiveId(1L)
            .dateFrom(Instant.parse("2022-02-22T19:34:50.630Z"))
            .dateTo(Instant.parse("2022-03-22T10:12:04.340Z"))
            .build();

    public static final HiveSnapshotDto HIVE_SNAPSHOT_DTO_1 = HiveSnapshotDto.builder()
            .hiveId(1L)
            .createdAt("2022-02-27T19:34:50.630Z")
            .temperature(30.3)
            .humidity(31.0)
            .co2(400.0)
            .honeyIncrease(0.21)
            .build();

    public static final HiveSnapshotDto HIVE_SNAPSHOT_DTO_2 = HiveSnapshotDto.builder()
            .hiveId(2L)
            .createdAt("2022-02-28T20:30:33.341Z")
            .temperature(31.2)
            .humidity(55.2)
            .co2(230.0)
            .honeyIncrease(0.18)
            .build();
}
