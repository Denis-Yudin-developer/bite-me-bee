package ru.coderiders.bitemebee.rest.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.coderiders.bitemebee.rest.api.UserApi;
import ru.coderiders.bitemebee.rest.dto.HiveRsDto;
import ru.coderiders.bitemebee.rest.dto.UserRqDto;
import ru.coderiders.bitemebee.rest.dto.UserRsDto;
import ru.coderiders.bitemebee.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    private final UserService userService;

    @Override
    public Page<UserRsDto> getAll(Pageable pageable) {
        return userService.getAll(pageable);
    }

    @Override
    public UserRsDto getById(Long id) {
        return userService.getById(id);
    }

    @Override
    public ResponseEntity<UserRsDto> create(UserRqDto userRqDto) {
        UserRsDto created = userService.create(userRqDto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @Override
    public UserRsDto update(Long id, UserRqDto userRqDto) {
        return userService.update(id, userRqDto);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        userService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
