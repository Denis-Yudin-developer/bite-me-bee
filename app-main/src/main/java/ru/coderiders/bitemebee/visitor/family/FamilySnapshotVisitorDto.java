package ru.coderiders.bitemebee.visitor.family;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamilySnapshotVisitorDto {
    @NotNull
    private Long hiveId;
    @NotNull
    private Integer populationDecreaseFactor;
    @NotNull
    private Integer queenIncreaseFactor;
}
