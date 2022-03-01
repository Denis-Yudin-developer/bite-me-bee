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
@Schema(description = "Входной DTO пчелиной семьи")
public class BeeFamilyRsDto {

  @Schema(description = "Идентификатор пчелиной семьи", example = "1")
  private Long id;

  @Schema(description = "DTO пчелиной семьи", example = "1")
  private BeeTypeRsDto beeTypeRsDto;

  @Schema(description = "Заметка о пчелиной семье",
    example = "Семья, которая постоянно болеет, но достаточно продуктивна на мёд", maxLength = 1000)
  private String note;

  @Schema(description = "Живая ли пчелиная семья", example = "true")
  private Boolean isAlive;
}
