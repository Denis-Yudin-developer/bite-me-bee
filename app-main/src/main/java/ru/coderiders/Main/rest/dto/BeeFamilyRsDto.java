package ru.coderiders.Main.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выходной DTO пчелиной семьи")
public class BeeFamilyRsDto {

    @Schema(description = "Идентификатор", example = "1")
    private Long id;

    @Schema(description = "Выходной DTO вида пчёл", example = "1")
    private BeeTypeRsDto beeType;

    @Schema(description = "Заметка о пчелиной семье",
            example = "Купили на Авито. Продавец Александр, 8(903)123-45-67", maxLength = 1000)
    private String note;

    @Schema(description = "Живёт ли пчелиная семья в улье", example = "true")
    private Boolean isAlive;

    @Schema(description = "Количество трутней", example = "32")
    private Long dronePopulation;

    @Schema(description = "Количество рабочих пчёл", example = "300")
    private Long workerPopulation;

    @Schema(description = "Количество маток", example = "1")
    private Long queenPopulation;

    @Schema(description = "Общая популяция пчелиной семьи", example = "333")
    private Long population;

}
