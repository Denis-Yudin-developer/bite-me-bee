package com.example.BiteMeBee.rest.api.impl;

import com.example.BiteMeBee.rest.api.BeeFamilyAPI;
import com.example.BiteMeBee.rest.dto.BeeFamilyNoteRqDto;
import com.example.BiteMeBee.rest.dto.BeeFamilyRqDto;
import com.example.BiteMeBee.rest.dto.BeeFamilyRsDto;
import com.example.BiteMeBee.rest.exception.BadRequestException;
import com.example.BiteMeBee.service.BeeFamilyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeeFamilyController implements BeeFamilyAPI {

    private final String BEE_FAMILY_NOT_CREATED = "Пчелиная семья не создана";
    private final BeeFamilyService beeFamilyService;

    @Override
    public ResponseEntity<BeeFamilyRsDto> create(BeeFamilyRqDto beeFamilyRqDto) {
        log.info("POST /bee_families, beeFamilyRqDto = {}", beeFamilyRqDto);

        return Optional.ofNullable(beeFamilyService.create(beeFamilyRqDto))
                .map(createdBeeFamily -> {
                    var link = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(createdBeeFamily.getId())
                            .toUri();
                    return ResponseEntity.created(link).body(createdBeeFamily);
                })
                .orElseThrow(() -> new BadRequestException(BEE_FAMILY_NOT_CREATED));
    }

    @Override
    public BeeFamilyRsDto release(Long id) {
        log.info("POST /bee_families/id, id = {}", id);

        return beeFamilyService.release(id);
    }

    @Override
    public BeeFamilyRsDto getById(Long id) {
        log.info("GET /bee_families/id, id = {}", id);

        return beeFamilyService.getById(id);
    }

    @Override
    public Page<BeeFamilyRsDto> getAll(Pageable pageable) {
        log.info("GET /bee_families, pageable = {}", pageable);

        return beeFamilyService.getAll(pageable);
    }

    @Override
    public BeeFamilyRsDto update(Long id, BeeFamilyNoteRqDto beeFamilyNoteRqDto) {
        log.info("UPDATE /bee_families/id, id = {}, beeFamilyNoteRqDto = {}", id, beeFamilyNoteRqDto);

        return beeFamilyService.update(id, beeFamilyNoteRqDto);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        log.info("DELETE /bee_families/id, id = {}", id);

        beeFamilyService.deleteById(id);
        return ResponseEntity.accepted().build();
    }
}
