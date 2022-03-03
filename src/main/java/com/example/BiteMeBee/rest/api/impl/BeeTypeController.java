package com.example.BiteMeBee.rest.api.impl;

import com.example.BiteMeBee.rest.api.BeeTypeApi;
import com.example.BiteMeBee.rest.dto.BeeTypeRqDto;
import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
import com.example.BiteMeBee.rest.exception.BadRequestException;
import com.example.BiteMeBee.service.impl.BeeTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BeeTypeController implements BeeTypeApi {

    private final BeeTypeServiceImpl beeTypeService;

    private final String BEE_TYPE_NOT_CREATED = "Вид пчелы не создан";

    @Override
    public Page<BeeTypeRsDto> getAll(Pageable pageable) {
        log.info("GET ALL /pageable={}", pageable);

        return beeTypeService.getAll(pageable);
    }

    @Override
    public BeeTypeRsDto getById(Long id) {
        log.info("GET /id={}", id);

        return beeTypeService.getById(id);
    }

    @Override
    public ResponseEntity<BeeTypeRsDto> create(BeeTypeRqDto beeTypeRqDto) {
        log.info("CREATE /beeTypeRqDto={}", beeTypeRqDto);

        return Optional.ofNullable(beeTypeService.create(beeTypeRqDto))
                .map(created -> {
                    var url = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(url).body(created);
                })
                .orElseThrow(() -> new BadRequestException(BEE_TYPE_NOT_CREATED));
    }

    @Override
    public BeeTypeRsDto update(Long id, BeeTypeRqDto beeTypeRqDto) {
        log.info("UPDATE /id={}", id);

        return beeTypeService.update(id, beeTypeRqDto);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        log.info("DELETE /id={}", id);

        beeTypeService.deleteById(id);
        return ResponseEntity.accepted()
                .build();
    }
}
