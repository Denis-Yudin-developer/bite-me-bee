package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coderiders.bitemebee.rest.dto.BeeTypeRqDto;
import ru.coderiders.bitemebee.rest.dto.BeeTypeRsDto;

public interface BeeTypeService {
    Page<BeeTypeRsDto> getAll(@NonNull Pageable pageable);

    BeeTypeRsDto getById(@NonNull Long id);

    BeeTypeRsDto create(@NonNull BeeTypeRqDto beeTypeRqDto);

    BeeTypeRsDto update(@NonNull Long id, @NonNull BeeTypeRqDto beeTypeRqDto);

    void deleteById(@NonNull Long id);
}
