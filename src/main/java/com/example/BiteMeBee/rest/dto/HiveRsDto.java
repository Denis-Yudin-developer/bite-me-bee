package com.example.BiteMeBee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выходной DTO улья")
public class HiveRsDto {

    @Schema(description = "Идентификатор улья", example = "1")
    private Long hiveId;

    @Schema(description = "Идентификатор пчелиной семьи", example = "1")
    private Long beeFamilyId;

    @Schema(description = "Количество рамок", example = "8", minimum = "1", maximum = "20")
    private Integer frameCount;
}
