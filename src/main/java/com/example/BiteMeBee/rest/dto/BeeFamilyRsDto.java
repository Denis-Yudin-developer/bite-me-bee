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

  @Schema(description = "Идентификатор пчелиной семьи")
  private Long id;

  @Schema(description = "Идентификатор пчелиной семьи")
  private Long beeTypeId;

  @Schema(description = "Заметка о пчелиной семье")
  private String note;

  @Schema(description = "Живая ли пчелиная семья")
  private Boolean status;
}
