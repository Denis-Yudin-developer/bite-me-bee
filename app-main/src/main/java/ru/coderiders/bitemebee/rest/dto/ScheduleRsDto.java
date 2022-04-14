package ru.coderiders.bitemebee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выходной Dto расписания плановой работы")
public class ScheduleRsDto {
    @Schema(description = "Идентификатор расписания плановой работы", example = "1")
    private Long id;
    @Schema(description = "Выходной Dto типовой работы")
    private ActivityRsDto activity;
    @Schema(description = "Интервал в минутах")
    private Long intervalInMinutes;
}
