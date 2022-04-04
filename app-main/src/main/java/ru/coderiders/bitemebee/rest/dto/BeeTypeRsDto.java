package ru.coderiders.BiteMeBee.rest.dto;

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
    @Schema(description = "Минимально комфортный уровень Co2", example = "310", minimum = "300", maximum = "700")
    private Integer minCo2;
    @Schema(description = "Максимально комфортный уровень Co2", example = "640", minimum = "300", maximum = "700")
    private Integer maxCo2;
    @Schema(description = "Минимально комфортный уровень влажности", example = "75", minimum = "70", maximum = "85")
    private Integer minHumidity;
    @Schema(description = "Максимально комфортный уровень влажности", example = "83", minimum = "70", maximum = "85")
    private Integer maxHumidity;
    @Schema(description = "Минимально комфортная температура", example = "27", minimum = "25", maximum = "35")
    private Integer minTemperature;
    @Schema(description = "Максимально комфортная температура", example = "34", minimum = "25", maximum = "35")
    private Integer maxTemperature;
    @Schema(description = "Коэффициент устойчивости к заморозкам", example = "0.9", minimum = "0.7", maximum = "1.3")
    private Double coldResistance;
    @Schema(description = "Коэффициент устойчивости к болезням", example = "0.9", minimum = "0.7", maximum = "1.3")
    private Double diseaseResistance;
    @Schema(description = "Коэффициент производительности мёда", example = "0.9", minimum = "0.7", maximum = "1.3")
    private Double honeyProductivity;
    @Schema(description = "Коэффициент яйценоскости", example = "0.9", minimum = "0.7", maximum = "1.3")
    private Double eggProductivity;
    @Schema(description = "Уровень агрессии", example = "0.9", minimum = "0.7", maximum = "1.3")
    private Double aggressionLevel;
    @Schema(description = "Коэффициент склонности к роению", example = "0.9", minimum = "0.7", maximum = "1.3")
    private Double roilingLevel;
}
