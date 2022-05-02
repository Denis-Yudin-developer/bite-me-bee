package ru.coderiders.bitemebee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO jwt токена")
public class JwtDto {
    @Schema(description = "Jwt токен", example = "some long JWT token..")
    private String token;
    private String type = "Bearer";
    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long id;
    @Schema(description = "Имя пользователя", example = "user")
    private String username;
    @Schema(description = "Список ролей")
    @Builder.Default
    private List<String> roles = Collections.emptyList();

    public JwtDto(String accessToken, Long id, String username, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}