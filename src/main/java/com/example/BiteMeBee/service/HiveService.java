package com.example.BiteMeBee.service;

import com.example.BiteMeBee.rest.dto.HiveRqDto;
import com.example.BiteMeBee.rest.dto.HiveRsDto;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HiveService {

    Page<HiveRsDto> getAll(@NonNull Pageable pageable);

    HiveRsDto getById(@NonNull Long id);

    HiveRsDto create(@NonNull HiveRqDto hiveRqDto);

    HiveRsDto update(@NonNull Long id, @NonNull HiveRqDto hiveRqDto);

    void deleteById(@NonNull Long id);
}