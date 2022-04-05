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
@Schema(description = "Выходной DTO типовой работы")
public class ActivityRsDto {
    @Schema(description = "Идентификатор записи", example = "1")
    private Long id;
    @Schema(description = "Тип работы", example = "Проветривание улья", maxLength = 100)
    private String title;
    @Schema(description = "Признак того, является ли работа плановой", example = "true")
    private Boolean isPlanned;
}
