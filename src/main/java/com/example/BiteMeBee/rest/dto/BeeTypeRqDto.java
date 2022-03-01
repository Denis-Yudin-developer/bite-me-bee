package com.example.BiteMeBee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входной DTO вида пчелы")
public class BeeTypeRqDto {

  @Size(max = 100, message
    = "Длинна названия вида должна быть не более 100 символов")
  @NotNull(message = "Не указано название вида")
  @Schema(description = "Название вида", example = "Медоносная пчела", maxLength = 100)
  private String title;

  @NotNull(message = "Не указано описание")
  @Schema(description = "Описание вида",
    example = "Люди разводят медоносных пчёл для получения продуктов пчеловодства: воска, мёда и других.")
  private String description;

  @Range(min=300,max=700, message = "Минимальный уровень Co2 должен быть в промежутке от 300 до 700")
  @NotNull(message = "Не указан минимальный уровень Co2")
  @Schema(description = "Минимальный уровень Co2", example = "310", minimum = "300", maximum = "700")
  private Integer minCo2;

  @Range(min=300,max=700, message = "Максимальный уровень Co2 должен быть в промежутке от 300 до 700")
  @NotNull(message = "Не указан максимальный уровень Co2")
  @Schema(description = "Максимальный уровень Co2", example = "640", minimum = "300", maximum = "700")
  private Integer maxCo2;

  @Range(min=70,max=85, message = "Минимальный уровень влажности должен быть в промежутке от 70% до 85%")
  @NotNull(message = "Не указана минимальная норма влажности")
  @Schema(description = "Минимальная норма влажности", example = "75", minimum = "70", maximum = "85")
  private Integer minHumidity;

  @Range(min=70,max=85, message = "Максимальный уровень влажности должен быть в промежутке от 70% до 85%")
  @NotNull(message = "Не указана максимальная норма влажности")
  @Schema(description = "Максимальная норма влажности", example = "83", minimum = "70", maximum = "85")
  private Integer maxHumidity;

  @Range(min=25,max=35, message = "Минимальная температура должна быть в промежутке от 25°C до 35°C")
  @NotNull(message = "Не указана минимальная температура")
  @Schema(description = "Минимальная температура", example = "27", minimum = "25", maximum = "35")
  private Integer minTemperature;

  @Range(min=25,max=35, message = "Максимальная температура должна быть в промежутке от 25°C до 35°C")
  @NotNull(message = "Не указана максимальная температура")
  @Schema(description = "Максимальная температура", example = "34", minimum = "25", maximum = "35")
  private Integer maxTemperature;

  @DecimalMin(value = "0.7", message = "Коэффициент сопротивления холода должен быть не меньше 0.7")
  @DecimalMax(value = "1.3", message = "Коэффициент сопротивления холода должен быть не больше 1.3")
  @NotNull(message = "Не указан коэффициент сопротивления холоду")
  @Schema(description = "Коэффициент сопротивление холоду", example = "0.9", minimum = "0.7", maximum = "1.3")
  private Double coldResistance;

  @DecimalMin(value = "0.7", message = "Коэффициент сопротивления болезням должен быть не меньше 0.7")
  @DecimalMax(value = "1.3", message = "Коэффициент сопротивления болезням должен быть не больше 1.3")
  @NotNull(message = "Не указан коэффициент сопротивления болезням")
  @Schema(description = "Коэффициент сопротивления болезням", example = "0.9", minimum = "0.7", maximum = "1.3")
  private Double diseaseResistance;

  @DecimalMin(value = "0.7", message = "Коэффициент производительности мёда должен быть не меньше 0.7")
  @DecimalMax(value = "1.3", message = "Коэффициент производительности мёда должен быть не больше 1.3")
  @NotNull(message = "Не указан коэффициент производительности мёда")
  @Schema(description = "Коэффициент производительности мёда", example = "0.9", minimum = "0.7", maximum = "1.3")
  private Double honeyProductivity;

  @DecimalMin(value = "0.7", message = "Коэффициент производительности яиц должен быть не меньше 0.7")
  @DecimalMax(value = "1.3", message = "Коэффициент производительности яиц должен быть не больше 1.3")
  @NotNull(message = "Не указан коэффициент производительности яиц")
  @Schema(description = "Коэффициент производительности яиц", example = "0.9", minimum = "0.7", maximum = "1.3")
  private Double eggProductivity;

  @DecimalMin(value = "0.7", message = "Коэффициент агрессии должен быть не меньше 0.7")
  @DecimalMax(value = "1.3", message = "Коэффициент агрессии должен быть не больше 1.3")
  @NotNull(message = "Не указан коэффициент агрессии")
  @Schema(description = "Уровень агрессии", example = "0.9", minimum = "0.7", maximum = "1.3")
  private Double aggressionLevel;

  @DecimalMin(value = "0.7", message = "Коэффициент роения должен быть не меньше 0.7")
  @DecimalMax(value = "1.3", message = "Коэффициент роения должен быть не больше 1.3")
  @NotNull(message = "Не указан коэффициент роения")
  @Schema(description = "Коэффициент роения", example = "0.9", minimum = "0.7", maximum = "1.3")
  private Double roilingLevel;
}
