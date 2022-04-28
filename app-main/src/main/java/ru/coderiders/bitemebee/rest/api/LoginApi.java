package ru.coderiders.bitemebee.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.coderiders.bitemebee.rest.dto.UserRqDto;
import ru.coderiders.bitemebee.rest.dto.UserRsDto;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/login")
@Tag(name = "Контроллер аккаунта", description = "Позволяет управлять аккаунтом")
public interface LoginApi {
    @PostMapping
    @Operation(description = "Войти в аккаунт", method = "POST")
    UserRsDto authorize(@RequestBody UserRqDto userRqDto, HttpServletRequest req);
}
