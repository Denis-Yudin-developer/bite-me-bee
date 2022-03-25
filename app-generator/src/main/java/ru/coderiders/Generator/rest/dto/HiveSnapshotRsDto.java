package ru.coderiders.Generator.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HiveSnapshotRsDto {

    private Long hiveId;

    private Instant createdAt;

    private Double temperature;

    private Double humidity;

    private Double co2;

    private Double honeyIncrease;
}