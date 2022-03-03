package com.example.BiteMeBee.service;

import com.example.BiteMeBee.rest.dto.BeeTypeRqDto;
import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BeeTypeService {

    Page<BeeTypeRsDto> getAll(@NonNull Pageable pageable);

    BeeTypeRsDto getById(@NonNull Long id);

    BeeTypeRsDto create(@NonNull BeeTypeRqDto beeTypeRqDto);

    BeeTypeRsDto update(@NonNull Long id, @NonNull BeeTypeRqDto beeTypeRqDto);

    void deleteById(@NonNull Long id);
}
