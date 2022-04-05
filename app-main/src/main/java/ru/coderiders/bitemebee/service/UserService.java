package ru.coderiders.BiteMeBee.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coderiders.BiteMeBee.rest.dto.UserRqDto;
import ru.coderiders.BiteMeBee.rest.dto.UserRsDto;

public interface UserService {
    Page<UserRsDto> getAll(@NonNull Pageable pageable);

    UserRsDto getById(@NonNull Long id);

    UserRsDto create(@NonNull UserRqDto userRqDto);

    UserRsDto update(@NonNull Long Id, @NonNull UserRqDto userRqDto);

    void deleteById(@NonNull Long Id);
}
