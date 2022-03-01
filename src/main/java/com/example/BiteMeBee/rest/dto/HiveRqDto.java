package com.example.BiteMeBee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входной DTO улья")
public class HiveRqDto {


  @NotNull(message = "Не указан идентификатор пчелиной семьи")
  @Schema(description = "Идентификатор пчелиной семьи", example = "1")
  private Long beeFamilyId;

  @Range(min=1,max=20, message = "Количество рамок должно быть в промежутке от 1 до 20")
  @NotNull(message = "Не указано количество рамок")
  @Schema(description = "Количество рамок", example = "8", minimum = "1", maximum = "20")
  private Integer frameCount;

}
