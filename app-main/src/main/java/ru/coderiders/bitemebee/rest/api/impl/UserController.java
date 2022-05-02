package ru.coderiders.bitemebee.rest.api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;
import ru.coderiders.bitemebee.rest.api.UserApi;
import ru.coderiders.bitemebee.rest.dto.UserDto;
import ru.coderiders.bitemebee.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return userService.getAll(pageable);
    }

    @Override
    public UserDto getById(Long id) {
        return userService.getById(id);
    }
}
