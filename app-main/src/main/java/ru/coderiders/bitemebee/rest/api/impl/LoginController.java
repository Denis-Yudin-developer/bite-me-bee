package ru.coderiders.bitemebee.rest.api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.coderiders.bitemebee.rest.api.LoginApi;
import ru.coderiders.bitemebee.rest.dto.UserRqDto;
import ru.coderiders.bitemebee.rest.dto.UserRsDto;
import ru.coderiders.bitemebee.service.LoginService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LoginController implements LoginApi {
    private final LoginService loginService;

    @Override
    public UserRsDto authorize(UserRqDto userRqDto, HttpServletRequest req) {
        return loginService.authorize(userRqDto, req);
    }
}
