package ru.coderiders.Generator.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GeneratorFamilyRqDto {

    private Long id;

    private Long hiveId;

    private Long dronePopulation;

    private Long workerPopulation;

    private Long queenPopulation;

    private Long population;

    private Double diseaseResistance;

    private Double honeyProductivity;

    private Double eggProductivity;
}
