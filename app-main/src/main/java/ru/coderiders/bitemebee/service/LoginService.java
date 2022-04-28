package ru.coderiders.bitemebee.service;

import org.springframework.http.ResponseEntity;
import ru.coderiders.bitemebee.rest.dto.UserRqDto;
import ru.coderiders.bitemebee.rest.dto.UserRsDto;

import javax.servlet.http.HttpServletRequest;

public interface LoginService {
    UserRsDto authorize(UserRqDto userRqDto, HttpServletRequest req);

    void login(HttpServletRequest req, String user, String pass);
}
