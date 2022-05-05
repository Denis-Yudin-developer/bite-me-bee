package ru.coderiders.bitemebee.visitor.hive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HiveSnapshotVisitorDto {
    @NotNull
    private Integer minTemperature;
    @NotNull
    private Integer maxTemperature;
    @NotNull
    private Integer honeyCapacity;
}
