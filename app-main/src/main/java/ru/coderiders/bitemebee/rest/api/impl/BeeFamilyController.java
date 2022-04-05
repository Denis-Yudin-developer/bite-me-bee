package ru.coderiders.bitemebee.rest.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.coderiders.bitemebee.rest.api.BeeFamilyAPI;
import ru.coderiders.bitemebee.rest.dto.BeeFamilyNoteRqDto;
import ru.coderiders.bitemebee.rest.dto.BeeFamilyRqDto;
import ru.coderiders.bitemebee.rest.dto.BeeFamilyRsDto;
import ru.coderiders.bitemebee.service.BeeFamilyService;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeeFamilyController implements BeeFamilyAPI {
    private final BeeFamilyService beeFamilyService;

    @Override
    public ResponseEntity<BeeFamilyRsDto> create(BeeFamilyRqDto beeFamilyRqDto) {
        BeeFamilyRsDto created = beeFamilyService.create(beeFamilyRqDto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @Override
    public BeeFamilyRsDto release(Long id) {
        return beeFamilyService.release(id);
    }

    @Override
    public BeeFamilyRsDto getById(Long id) {
        return beeFamilyService.getById(id);
    }

    @Override
    public Page<BeeFamilyRsDto> getAll(Pageable pageable) {
        return beeFamilyService.getAll(pageable);
    }

    @Override
    public BeeFamilyRsDto update(Long id, BeeFamilyNoteRqDto beeFamilyNoteRqDto) {
        return beeFamilyService.update(id, beeFamilyNoteRqDto);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        beeFamilyService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
