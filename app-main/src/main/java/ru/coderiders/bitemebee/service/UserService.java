package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.coderiders.bitemebee.rest.dto.UserDto;

public interface UserService extends UserDetailsService {
    Page<UserDto> getAll(@NonNull Pageable pageable);

    UserDto getById(@NonNull Long id);

    Long getRandomUserId();
}
