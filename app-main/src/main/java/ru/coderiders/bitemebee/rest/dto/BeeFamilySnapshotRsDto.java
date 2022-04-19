package ru.coderiders.bitemebee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выходной DTO снимка семьи")
public class BeeFamilySnapshotRsDto {

    @Schema(description = "Дата снимка", example = "2022-03-14T18:30:50.630Z")
    private Instant createdAt;

    @Schema(description = "Прирост числа пчел в улье", example = "5", minimum = "0.0")
    private Long populationIncrease;

    @Schema(description = "Прирост числа трутней в улье", example = "5", minimum = "0.0")
    private Long dronePopulationIncrease;

    @Schema(description = "Прирост числа рабочих пчел в улье", example = "5", minimum = "0.0")
    private Long workerPopulationIncrease;

    @Schema(description = "Прирост числа маток в улье", example = "5", minimum = "0.0")
    private Long queenPopulationIncrease;

    @Schema(description = "Активность пчел в улье", example = "1.0", minimum = "0.7", maximum = "1.3")
    private Double activity;

    @Schema(description = "Состояние пчел в улье", example = "1.0", minimum = "0.7", maximum = "1.3")
    private Double mood;
}
