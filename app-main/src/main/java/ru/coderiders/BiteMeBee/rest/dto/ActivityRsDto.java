package ru.coderiders.BiteMeBee.rest.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выходной DTO типа работы")
public class ActivityRsDto {

    @Schema(description = "Идентификатор работы", example = "1")
    private Long id;

    @Schema(description = "Тип работы", example = "Проветривание улья", maxLength = 100)
    private String title;

    @Schema(description = "Указание того, является ли работа плановой", example = "Работа плановая")
    private Boolean isPlanned;
}
