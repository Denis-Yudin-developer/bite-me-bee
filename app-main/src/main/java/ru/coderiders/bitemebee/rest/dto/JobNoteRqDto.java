package ru.coderiders.bitemebee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входной DTO заметки о работе")
public class JobNoteRqDto {
    @Size(max = 1000, message = "Максимальная длина заметки — 1000 символов")
    @NotNull(message = "Не указана заметка о работе")
    @Schema(description = "Заметка о работе",
            example = "Нужно снять мед с улья номер 23", maxLength = 1000)
    private String note;
}
