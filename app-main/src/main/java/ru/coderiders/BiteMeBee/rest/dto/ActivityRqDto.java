package ru.coderiders.BiteMeBee.rest.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входной DTO типа работы")
public class ActivityRqDto {

    @Size(max = 100, message
            = "Длина описания работы должна быть не более 100 символов")
    @NotNull(message = "Не указан тип работы")
    @Schema(description = "Тип работы", example = "Проветривание улья", maxLength = 100)
    private String title;

    @NotNull(message = "Не указано, является ли работа плановой")
    @Schema(description = "Указание того, является ли работа плановой", example = "Работа плановая")
    private Boolean isPlanned;
}
