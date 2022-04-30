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
@Schema(description = "Выходной DTO работы на пасеке")
public class JwtDto {
    @Schema(description = "Jwt токен")
    private String token;
    private String type = "Bearer";
    @Schema(description = "1")
    private Long id;
    @Schema(description = "user")
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