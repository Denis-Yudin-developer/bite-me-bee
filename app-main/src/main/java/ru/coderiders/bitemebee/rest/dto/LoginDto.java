package ru.coderiders.bitemebee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO авторизации")
public class LoginDto {
    @NotNull(message = "Не указано имя пользователя")
    @Schema(description = "Имя пользователя", example = "userName123")
    private String username;

    @NotNull(message = "Не указан пароль пользователя")
    @Schema(description = "Пароль пользователя", example = "qweasd123")
    private String password;
}
