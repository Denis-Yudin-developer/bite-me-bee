package ru.coderiders.bitemebee.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.coderiders.bitemebee.rest.dto.LoginDto;
import ru.coderiders.bitemebee.rest.dto.SignupDto;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
@Tag(name = "Контроллер авторизации", description = "Позволяет управлять аккаунтом")
public interface AuthApi {
    @PostMapping("/login")
    @Operation(description = "Авторизировать пользователя", method = "POST")
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto);

    @PostMapping("/signup")
    @Operation(description = "Зарегистрировать пользователя", method = "POST")
    ResponseEntity<?> registerUser(@Valid @RequestBody SignupDto signUpDto);
}
