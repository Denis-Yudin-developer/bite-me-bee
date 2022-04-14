package ru.coderiders.bitemebee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входной Dto расписания плановой работы")
public class ScheduleRqDto {
    @NotNull(message = "Не указан идентификатор типовой работы")
    @Schema(description = "Идентификатор типовой работы", example = "1")
    private Long activityId;
    @Min(value = 1, message = "Минимальный интервал — 1 минута")
    @Schema(description = "Интервал выполнения плановой работы в минутах", example = "15", minimum = "1")
    private Long intervalInMinutes;
}
