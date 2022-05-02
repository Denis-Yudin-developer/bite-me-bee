package ru.coderiders.bitemebee.service;

import org.springframework.lang.NonNull;
import ru.coderiders.bitemebee.rest.dto.JwtDto;
import ru.coderiders.bitemebee.rest.dto.LoginDto;
import ru.coderiders.bitemebee.rest.dto.RegisterDto;
import ru.coderiders.bitemebee.rest.dto.UserDto;

public interface AuthService {
    JwtDto login(@NonNull LoginDto loginDto);

    UserDto register(@NonNull RegisterDto signUpDto);
}
