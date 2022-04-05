package ru.coderiders.BiteMeBee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выходной DTO записи о пользователе")
public class UserRsDto {
    @Schema(description = "Идентификатор записи", example = "1")
    private Long id;
    @Schema(description = "Юзернейм пользователя", example = "userName123")
    private String username;
    @Schema(description = "Email пользователя", example = "email@gmail.com")
    private String email;
    @Schema(description = "Пароль пользователя", example = "qweasd123")
    private String password;
    @Schema(description = "Роль пользователя", example = "Admin")
    private String roleId;
}
