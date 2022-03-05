package com.example.BiteMeBee.rest.api.impl;

import com.example.BiteMeBee.rest.api.HiveApi;
import com.example.BiteMeBee.rest.dto.HiveRqDto;
import com.example.BiteMeBee.rest.dto.HiveRsDto;
import com.example.BiteMeBee.rest.exception.BadRequestException;
import com.example.BiteMeBee.service.HiveService;
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
public class HiveController implements HiveApi {

    private final HiveService hiveService;

    private final String HIVE_NOT_CREATED = "Улей не создан";

    @Override
    public Page<HiveRsDto> getAll(Pageable pageable) {
        log.info("GET ALL /pageable={}", pageable);

        return hiveService.getAll(pageable);
    }

    @Override
    public HiveRsDto getById(Long id) {
        log.info("GET /id={}", id);

        return hiveService.getById(id);
    }

    @Override
    public ResponseEntity<HiveRsDto> create(HiveRqDto hiveRqDto) {
        log.info("CREATE /hiveRqDto={}", hiveRqDto);

        return Optional.ofNullable(hiveService.create(hiveRqDto))
                .map(created -> {
                    var url = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(url).body(created);
                })
                .orElseThrow(() -> new BadRequestException(HIVE_NOT_CREATED));
    }

    @Override
    public HiveRsDto update(Long id, HiveRqDto hiveRqDto) {
        log.info("UPDATE /id={}", id);

        return hiveService.update(id, hiveRqDto);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        log.info("DELETE /id={}", id);

        hiveService.deleteById(id);
        return ResponseEntity.accepted()
                .build();
    }
}
