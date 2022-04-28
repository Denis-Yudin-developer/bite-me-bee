package ru.coderiders.bitemebee.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("/api/logout")
@Tag(name = "Контроллер аккаунта", description = "Позволяет управлять аккаунтом")
public interface LogoutApi {
    @GetMapping
    @Operation(description = "Выйти из аккаунта", method = "GET")
    void customLogout(HttpServletRequest request, HttpServletResponse response);
}
