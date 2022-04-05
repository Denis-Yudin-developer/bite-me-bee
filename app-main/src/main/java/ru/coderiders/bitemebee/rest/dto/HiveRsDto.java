package ru.coderiders.bitemebee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выходной DTO улья")
public class HiveRsDto {
    @Schema(description = "Идентификатор улья", example = "1")
    private Long id;
    @Schema(description = "Список пчелиных семей")
    @Builder.Default
    private List<BeeFamilyRsDto> beeFamilies = Collections.emptyList();
    @Schema(description = "Имя улья", example = "Большой жуж", maxLength = 50)
    private String name;
    @Schema(description = "Количество рамок", example = "8", minimum = "1", maximum = "20")
    private Integer frameCount;
    @Schema(description = "Количество мёда", example = "4.9", minimum = "0.0", maximum = "30.0")
    private Double honeyAmount;
}
