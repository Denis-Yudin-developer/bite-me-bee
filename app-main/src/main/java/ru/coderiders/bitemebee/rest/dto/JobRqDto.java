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
@Schema(description = "Входной DTO работы на пасеке")
public class JobRqDto {
    @NotNull(message = "Не указан идентификатор типовой работы")
    @Schema(description = "Идентификатор типовой работы", example = "1")
    private Long activityId;
    @Size(max = 1000, message = "Максимальная длина заметки — 1000 символов")
    @NotNull(message = "Не указан заметка о работе")
    @Schema(description = "Описание работы",
            example = "Нужно снять мед с улья номер 23", maxLength = 1000)
    private String note;
    @NotNull(message = "Не указан идентификатор улья")
    @Schema(description = "Идентификатор улья", example = "1")
    private Long hiveId;
    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long userId;
}
