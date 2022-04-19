package ru.coderiders.bitemebee.rest.dto;

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
@Schema(description = "Входной DTO запроса снимков семьи")
public class BeeFamilySnapshotRqDto {

    @NotNull(message = "Не указан идентификатор семьи")
    @Schema(description = "Идентификатор семьи", example = "1")
    private Long familyId;

    @NotNull(message = "Не указана дата начала периода")
    @Schema(description = "Дата начала периода", example = "2022-03-14T18:30:50.630Z")
    private Instant dateFrom;

    @NotNull(message = "Не указана дата окончания периода")
    @Schema(description = "Дата окончания периода", example = "2022-03-29T15:30:04.340Z")
    private Instant dateTo;
}
