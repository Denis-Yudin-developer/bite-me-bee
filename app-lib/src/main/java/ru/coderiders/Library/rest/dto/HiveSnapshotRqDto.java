package ru.coderiders.Library.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входной DTO запроса снимков улья")
public class HiveSnapshotRqDto {

    @NotNull(message = "Не указан идентификатор улья")
    @Schema(description = "Идентификатор улья", example = "1")
    private Long hiveId;

    @NotNull(message = "Не указана дата начала периода")
    @Schema(description = "Дата начала периода", example = "2022-02-27T19:34:50.630Z")
    private Instant dateFrom;

    @NotNull(message = "Не указана дата окончания периода")
    @Schema(description = "Дата окончания периода", example = "2022-03-22T10:12:04.340Z")
    private Instant dateTo;
}
