package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coderiders.bitemebee.rest.dto.UserRqDto;
import ru.coderiders.bitemebee.rest.dto.UserRsDto;

public interface UserService {
    Page<UserRsDto> getAll(@NonNull Pageable pageable);

    UserRsDto getById(@NonNull Long id);

    UserRsDto create(@NonNull UserRqDto userRqDto);

    UserRsDto update(@NonNull Long id, @NonNull UserRqDto userRqDto);

    void deleteById(@NonNull Long id);
}
