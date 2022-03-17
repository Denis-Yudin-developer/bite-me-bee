package ru.coderiders.Main.rest.api.impl;

import ru.coderiders.Main.rest.api.HiveApi;
import ru.coderiders.Main.rest.dto.HiveRqDto;
import ru.coderiders.Main.rest.dto.HiveRsDto;
import ru.coderiders.Main.service.HiveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HiveController implements HiveApi {

    private final HiveService hiveService;

    @Override
    public Page<HiveRsDto> getAll(Pageable pageable) {
        return hiveService.getAll(pageable);
    }

    @Override
    public HiveRsDto getById(Long id) {
        return hiveService.getById(id);
    }

    @Override
    public ResponseEntity<HiveRsDto> create(HiveRqDto hiveRqDto) {

        HiveRsDto created = hiveService.create(hiveRqDto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @Override
    public HiveRsDto update(Long id, HiveRqDto hiveRqDto) {
        return hiveService.update(id, hiveRqDto);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        hiveService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}