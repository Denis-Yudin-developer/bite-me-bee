package ru.coderiders.BiteMeBee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входной DTO пчелиной семьи")
public class BeeFamilyRqDto {
    @NotNull(message = "Не указан идентификатор вида пчёл")
    @Schema(description = "Идентификатор вида пчёл", example = "1")
    private Long beeTypeId;
    @NotNull(message = "Не указан идентификатор улья")
    @Schema(description = "Идентификатор улья", example = "1")
    private Long hiveId;
    @Size(max = 1000, message = "Максимальная длина заметки — 1000 символов")
    @NotNull(message = "Не указана заметка о пчелиной семье")
    @Schema(description = "Заметка о пчелиной семье",
            example = "Купили на Авито. Продавец Александр, 8(903)123-45-67", maxLength = 1000)
    private String note;
    @Min(value = 0, message = "Количество трутней должно быть положительным числом")
    @NotNull(message = "Не указано количество трутней")
    @Schema(description = "Количество трутней", example = "0")
    private Long dronePopulation;
    @Min(value = 1, message = "Количество рабочих пчёл должно быть больше нуля")
    @NotNull(message = "Не указано количество рабочих пчёл")
    @Schema(description = "Количество рабочих пчёл", example = "99")
    private Long workerPopulation;
    @Range(min = 1, max = 1, message = "В новой пчелиной семье может быть только одна матка")
    @NotNull(message = "Не указано количество маток")
    @Schema(description = "Количество маток", example = "1")
    private Long queenPopulation;
}
