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
@Schema(description = "Входной DTO записи о пользователе")
public class UserRqDto {
    @NotNull(message = "Не указан юзернейм пользователя")
    @Schema(description = "Юзернейм пользоватея", example = "userName123")
    private String username;
    //TODO повесть регулярку на эмаил
    @NotNull(message = "Не указан email пользователя")
    @Schema(description = "email пользователя", example = "email@gmail.com")
    private String email;
    //TODO повесить регулярку на password
    @NotNull(message = "Не указан пароль пользователя")
    @Schema(description = "Пароль пользователя", example = "qweasd123")
    private String password;
    @NotNull(message = "Не указана роль пользователя")
    @Schema(description = "Роль пользователя", example = "Admin")
    private String roleId;
}
