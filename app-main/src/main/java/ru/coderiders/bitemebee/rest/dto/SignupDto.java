package ru.coderiders.bitemebee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.Set;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO регистрации")
public class SignupDto {
    @Size(min = 2, max = 15,  message = "Длина имени должна быть в диапозоне от 2 до 15 символов")
    @NotNull(message = "Не указано имя пользователя")
    @Schema(description = "Имя пользователя", example = "userName123")
    private String username;
    @Builder.Default
    @Schema(description = "Массив с интервалами для типовых работ")
    private Set<String> role = Collections.emptySet();
    @Size(min = 4, max = 20,  message = "Длина пароля должна быть в диапозоне от 4 до 20 символов")
    @NotNull(message = "Не указан пароль пользователя")
    @Schema(description = "Пароль пользователя", example = "qweasd123")
    private String password;
}
