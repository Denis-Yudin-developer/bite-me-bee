package com.example.BiteMeBee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выходной DTO улья")
public class HiveRsDto {

    @Schema(description = "Идентификатор улья", example = "1")
    private Long id;

    @Schema(description = "Список пчелиных семей", example = "1")
    private List<BeeFamilyRsDto> beeFamilies;

    @Schema(description = "Количество рамок", example = "8", minimum = "1", maximum = "20")
    private Integer frameCount;
}
