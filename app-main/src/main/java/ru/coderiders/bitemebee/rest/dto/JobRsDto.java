package ru.coderiders.bitemebee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выходной DTO работы на пасеке")
public class JobRsDto {
    @Schema(description = "Идентификатор работы", example = "1")
    private Long id;
    @Schema(description = "Идентификатор типовой работы", example = "1")
    private ActivityRsDto activity;
    @Size(max = 1000, message = "Максимальная длина заметки — 1000 символов")
    @NotNull(message = "Не указано описание работы")
    @Schema(description = "Описание работы",
            example = "Нужно снять мед с улья номер 23", maxLength = 1000)
    private String note;
    @Schema(description = "Идентификатор улья", example = "1")
    private HiveRsDto hive;

    //TODO поле userId

    @NotNull(message = "Не указано время создания работы")
    @Schema(description = "Время создания работы", example = "2022-03-14T18:30:50.630Z")
    private Instant createdAt;
    @Schema(description = "Время завершения работы", example = "2022-03-29T18:30:50.630Z")
    private Instant closedAt;
    @Schema(description = "Статус работы", example = "true")
    private Boolean isCompleted;
}
