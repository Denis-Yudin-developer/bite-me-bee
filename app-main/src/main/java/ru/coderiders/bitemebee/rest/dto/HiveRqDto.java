package ru.coderiders.BiteMeBee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Входной DTO улья")
public class HiveRqDto {
    @Size(max = 50, message = "Имя улья должно быть не более 50 символов")
    @NotNull(message = "Не указано имя улья")
    @Schema(description = "Имя улья", example = "Большой жуж", maxLength = 50)
    private String name;
    @Range(min = 1, max = 20, message = "Количество рамок должно быть в промежутке от 1 до 20")
    @NotNull(message = "Не указано количество рамок")
    @Schema(description = "Количество рамок", example = "8", minimum = "1", maximum = "20")
    private Integer frameCount;
}
