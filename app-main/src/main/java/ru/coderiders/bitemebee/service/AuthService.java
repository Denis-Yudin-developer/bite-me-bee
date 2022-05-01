package ru.coderiders.bitemebee.service;

import org.springframework.lang.NonNull;
import ru.coderiders.bitemebee.rest.dto.JwtDto;
import ru.coderiders.bitemebee.rest.dto.LoginDto;
import ru.coderiders.bitemebee.rest.dto.SignupDto;
import ru.coderiders.bitemebee.rest.dto.UserDto;

public interface AuthService {
    JwtDto authenticateUser(@NonNull LoginDto loginDto);

    UserDto registerUser(@NonNull SignupDto signUpDto);
}
