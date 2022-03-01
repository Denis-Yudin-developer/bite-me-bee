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
@Schema(description = "Выходной DTO вида пчелы")
public class BeeTypeRsDto {

  @Schema(description = "Идентификатор вида", example = "1")
  private Long id;

  @Schema(description = "Название вида", example = "Медоносная пчела", maxLength = 100)
  private String title;

  @Schema(description = "Описание вида",
    example = "Люди разводят медоносных пчёл для получения продуктов пчеловодства: воска, мёда и других.")
  private String description;

  @Schema(description = "Минимальный уровень Co2", example = "310", minimum = "300", maximum = "700")
  private Integer minCo2;

  @Schema(description = "Максимальный уровень Co2", example = "640", minimum = "300", maximum = "700")
  private Integer maxCo2;

  @Schema(description = "Минимальная норма влажности", example = "75", minimum = "70", maximum = "85")
  private Integer minHumidity;

  @Schema(description = "Максимальная норма влажности", example = "83", minimum = "70", maximum = "85")
  private Integer maxHumidity;

  @Schema(description = "Минимальная температура", example = "27", minimum = "25", maximum = "35")
  private Integer minTemperature;

  @Schema(description = "Максимальная температура", example = "34", minimum = "25", maximum = "35")
  private Integer maxTemperature;

  @Schema(description = "Коэффициент сопротивление холоду", example = "0.9", minimum = "0.7", maximum = "1.3")
  private Double coldResistance;

  @Schema(description = "Коэффициент сопротивления болезням", example = "0.9", minimum = "0.7", maximum = "1.3")
  private Double diseaseResistance;

  @Schema(description = "Коэффициент производительности мёда", example = "0.9", minimum = "0.7", maximum = "1.3")
  private Double honeyProductivity;

  @Schema(description = "Коэффициент производительности яиц", example = "0.9", minimum = "0.7", maximum = "1.3")
  private Double eggProductivity;

  @Schema(description = "Уровень агрессии", example = "0.9", minimum = "0.7", maximum = "1.3")
  private Double aggressionLevel;

  @Schema(description = "Коэффициент роения", example = "0.9", minimum = "0.7", maximum = "1.3")
  private Double roilingLevel;
}
