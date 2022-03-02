package com.example.BiteMeBee.service;

import com.example.BiteMeBee.rest.dto.BeeTypeRqDto;
import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BeeTypeService {

    Page<BeeTypeRsDto> getAll(Pageable pageable);

    BeeTypeRsDto getById(Long id);

    BeeTypeRsDto create(BeeTypeRqDto beeTypeRqDto);

    BeeTypeRsDto update(BeeTypeRqDto beeTypeRqDto, Long id);

    void deleteById(Long id);
}
