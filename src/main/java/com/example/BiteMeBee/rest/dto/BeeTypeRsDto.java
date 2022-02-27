package com.example.BiteMeBee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response dto вида пчелы")
public class BeeTypeRsDto {

  @Schema(description = "Идентификатор вида")
  private Long id;

  @Schema(description = "Название вида")
  private String title;

  @Schema(description = "Описание вида")
  private String description;

  @Schema(description = "Минимальный уровень Co2")
  private Integer minCo2;

  @Schema(description = "Максимальный уровень Co2")
  private Integer maxCo2;

  @Schema(description = "Минимальная норма влажности")
  private Integer min_Humidity;

  @Schema(description = "Максимальная норма влажности")
  private Integer max_Humidity;

  @Schema(description = "Минимальная температура")
  private Integer minTemperature;

  @Schema(description = "Максимальная температура")
  private Integer maxTemperature;

  @Schema(description = "Коэффициент сопротивление холоду")
  private Double coldResistance;

  @Schema(description = "Коэффициент сопротивления болезням")
  private Double diseaseResistance;

  @Schema(description = "Коэффициент производительности мёда")
  private Double honeyProductivity;

  @Schema(description = "Коэффициент производительности яиц")
  private Double eggProductivity;

  @Schema(description = "Коэффициент агрессии")
  private Double aggression;

  @Schema(description = "Коэффициент роения")
  private Double roiling;
}
