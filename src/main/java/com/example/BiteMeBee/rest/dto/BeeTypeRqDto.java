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
@Schema(description = "Request dto вида пчелы")
public class BeeTypeRqDto {

  @Size(min = 1, max = 100, message
    = "Длинна названия вида должна быть в промежутке от 1 до 100 символов")
  @NotNull(message = "Не указано название вида")
  @Schema(description = "Название вида")
  private String title;

  @Size(min = 1, max = 3000, message
    = "Длинна описания вида должна быть в промежутке от 1 до 3000 символов")
  @NotNull(message = "Не указано описание")
  @Schema(description = "Описание вида")
  private String description;

  @Min(value = 1, message = "Минимальный уровень Co2 должен быть не ниже 1")
  @Max(value = 10000, message = "Минимальный уровень Co2 должен быть не выше 10000")
  @NotNull(message = "Не указан минимальный уровень Co2")
  @Schema(description = "Минимальный уровень Co2")
  private Integer minCo2;

  @Min(value = 1, message = "Максимальный уровень Co2 должен быть не ниже 1")
  @Max(value = 10000, message = "Максимальный уровень Co2 должен быть не выше 10000")
  @NotNull(message = "Не указан максимальный уровень Co2")
  @Schema(description = "Максимальный уровень Co2")
  private Integer maxCo2;

  @Min(value = 0, message = "Минимальный уровень влажности должен быть не ниже 0%")
  @Max(value = 100, message = "Минимальный уровень влажности должен быть не выше 100%")
  @NotNull(message = "Не указана минимальная норма влажности")
  @Schema(description = "Минимальная норма влажности")
  private Integer min_Humidity;

  @Min(value = 0, message = "Максимальный уровень влажности должен быть не ниже 0%")
  @Max(value = 100, message = "Максимальный уровень влажности должен быть не выше 100%")
  @NotNull(message = "Не указана максимальная норма влажности")
  @Schema(description = "Максимальная норма влажности")
  private Integer max_Humidity;

  @Min(value = -100, message = "Минимальная температура должна быть не ниже -60°C")
  @Max(value = 100, message = "Минимальная температура должна быть не выше 100°C")
  @NotNull(message = "Не указана минимальная температура")
  @Schema(description = "Минимальная температура")
  private Integer minTemperature;

  @Min(value = -100, message = "Минимальная температура должна быть не ниже -60°C")
  @Max(value = 100, message = "Максимальная температура должна быть не выше 100°C")
  @NotNull(message = "Не указана максимальная температура")
  @Schema(description = "Максимальная температура")
  private Integer maxTemperature;

  @Min(value = 0, message = "Коэффициент сопротивления холода должен быть не меньше 0")
  @Max(value = 1, message = "Коэффициент сопротивления холода должен быть не больше 1")
  @NotNull(message = "Не указан коэффициент сопротивления холоду")
  @Schema(description = "Коэффициент сопротивление холоду")
  private Double coldResistance;

  @Min(value = 0, message = "Коэффициент сопротивления болезням должен быть не меньше 0")
  @Max(value = 1, message = "Коэффициент сопротивления болезням должен быть не больше 1")
  @NotNull(message = "Не указан коэффициент сопротивления болезням")
  @Schema(description = "Коэффициент сопротивления болезням")
  private Double diseaseResistance;

  @Min(value = 0, message = "Коэффициент производительности мёда должен быть не меньше 0")
  @Max(value = 1, message = "Коэффициент производительности мёда должен быть не больше 1")
  @NotNull(message = "Не указан коэффициент производительности мёда")
  @Schema(description = "Коэффициент производительности мёда")
  private Double honeyProductivity;

  @Min(value = 0, message = "Коэффициент производительности яиц должен быть не меньше 0")
  @Max(value = 1, message = "Коэффициент производительности яиц должен быть не больше 1")
  @NotNull(message = "Не указан коэффициент производительности яиц")
  @Schema(description = "Коэффициент производительности яиц")
  private Double eggProductivity;

  @Min(value = 0, message = "Коэффициент агрессии должен быть не меньше 0")
  @Max(value = 1, message = "Коэффициент агрессии должен быть не больше 1")
  @NotNull(message = "Не указан коэффициент агрессии")
  @Schema(description = "Коэффициент агрессии")
  private Double aggression;

  @Min(value = 0, message = "Коэффициент роения должен быть не меньше 0")
  @Max(value = 1, message = "Коэффициент роения должен быть не больше 1")
  @NotNull(message = "Не указан коэффициент роения")
  @Schema(description = "Коэффициент роения")
  private Double roiling;
}
