package ru.coderiders.bitemebee.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Выходной DTO записи о пользователе")
public class UserDto {
    @Schema(description = "Идентификатор записи", example = "1")
    private Long id;
    @Schema(description = "Имя пользователя", example = "userName123")
    private String username;
}
