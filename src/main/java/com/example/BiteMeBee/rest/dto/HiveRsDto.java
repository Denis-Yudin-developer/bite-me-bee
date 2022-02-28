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

  @Schema(description = "Идентификатор улья")
  private Long hiveId;

  @Schema(description = "Идентификатор пчелиной семьи")
  private Long beeFamilyId;

  @Schema(description = "Количество рамок")
  private Integer frameCount;
}
