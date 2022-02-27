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
    = "Длинна названия вида должна быть в промежутке от 1 до 100 символов")
  @NotNull(message = "Не указано название вида")
  @Schema(description = "Название вида")
  private String title;

  @Size(min = 1, message = "Длинна описания вида должна быть в промежутке от 1 до 3000 символов")
  @NotNull(message = "Не указано описание")
  @Schema(description = "Описание вида")
  private String description;

  @Range(min=1,max=10000, message = "Минимальный уровень Co2 должен быть в промежутке от 1 до 10000")
  @NotNull(message = "Не указан минимальный уровень Co2")
  @Schema(description = "Минимальный уровень Co2")
  private Integer minCo2;

  @Range(min=1,max=10000, message = "Максимальный уровень Co2 должен быть в промежутке от 1 до 10000")
  @NotNull(message = "Не указан максимальный уровень Co2")
  @Schema(description = "Максимальный уровень Co2")
  private Integer maxCo2;

  @Range(min=0,max=100, message = "Минимальный уровень влажности должен быть в промежутке от 0% до 100%")
  @NotNull(message = "Не указана минимальная норма влажности")
  @Schema(description = "Минимальная норма влажности")
  private Integer minHumidity;

  @Range(min=0,max=100, message = "Максимальный уровень влажности должен быть в промежутке от 0% до 100%")
  @NotNull(message = "Не указана максимальная норма влажности")
  @Schema(description = "Максимальная норма влажности")
  private Integer maxHumidity;

  @Range(min=-100,max=100, message = "Минимальная температура должна быть в промежутке от -100°C до 100°C")
  @NotNull(message = "Не указана минимальная температура")
  @Schema(description = "Минимальная температура")
  private Integer minTemperature;

  @Range(min=-100,max=100, message = "Максимальная температура должна быть в промежутке от -100°C до 100°C")
  @NotNull(message = "Не указана максимальная температура")
  @Schema(description = "Максимальная температура")
  private Integer maxTemperature;

  @DecimalMin(value = "0.7", message = "Коэффициент сопротивления холода должен быть не меньше 0.7")
  @DecimalMax(value = "1.3", message = "Коэффициент сопротивления холода должен быть не больше 1.3")
  @NotNull(message = "Не указан коэффициент сопротивления холоду")
  @Schema(description = "Коэффициент сопротивление холоду")
  private Double coldResistance;

  @DecimalMin(value = "0.7", message = "Коэффициент сопротивления болезням должен быть не меньше 0.7")
  @DecimalMax(value = "1.3", message = "Коэффициент сопротивления болезням должен быть не больше 1.3")
  @NotNull(message = "Не указан коэффициент сопротивления болезням")
  @Schema(description = "Коэффициент сопротивления болезням")
  private Double diseaseResistance;

  @DecimalMin(value = "0.7", message = "Коэффициент производительности мёда должен быть не меньше 0.7")
  @DecimalMax(value = "1.3", message = "Коэффициент производительности мёда должен быть не больше 1.3")
  @NotNull(message = "Не указан коэффициент производительности мёда")
  @Schema(description = "Коэффициент производительности мёда")
  private Double honeyProductivity;

  @DecimalMin(value = "0.7", message = "Коэффициент производительности яиц должен быть не меньше 0.7")
  @DecimalMax(value = "1.3", message = "Коэффициент производительности яиц должен быть не больше 1.3")
  @NotNull(message = "Не указан коэффициент производительности яиц")
  @Schema(description = "Коэффициент производительности яиц")
  private Double eggProductivity;

  @DecimalMin(value = "0.7", message = "Коэффициент агрессии должен быть не меньше 0.7")
  @DecimalMax(value = "1.3", message = "Коэффициент агрессии должен быть не больше 1.3")
  @NotNull(message = "Не указан коэффициент агрессии")
  @Schema(description = "Уровень агрессии")
  private Double aggressionLevel;

  @DecimalMin(value = "0.7", message = "Коэффициент роения должен быть не меньше 0.7")
  @DecimalMax(value = "1.3", message = "Коэффициент роения должен быть не больше 1.3")
  @NotNull(message = "Не указан коэффициент роения")
  @Schema(description = "Коэффициент роения")
  private Double roilingLevel;
}
