package ru.coderiders.commons.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выходной DTO снимка улья")
public class HiveSnapshotDto {
    @Schema(description = "Идентификатор улья", example = "1")
    private Long hiveId;
    @Schema(description = "Дата снимка", example = "2022-02-27T19:34:50.630Z")
    private String createdAt;
    @Schema(description = "Температура в улье", example = "30.3", minimum = "-40.0", maximum = "60.0")
    private Double temperature;
    @Schema(description = "Влажность в улье", example = "31.0", minimum = "0.0", maximum = "100.0")
    private Double humidity;
    @Schema(description = "Содержание Co2 в улье", example = "400.0", minimum = "100.0", maximum = "1000.0")
    private Double co2;
    @Schema(description = "Прирост мёда в улье", example = "0.21", minimum = "0.0", maximum = "5.0")
    private Double honeyIncrease;
}