package ru.coderiders.BiteMeBee.rest.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.coderiders.BiteMeBee.rest.api.BeeFamilyAPI;
import ru.coderiders.BiteMeBee.rest.dto.BeeFamilyNoteRqDto;
import ru.coderiders.BiteMeBee.rest.dto.BeeFamilyRqDto;
import ru.coderiders.BiteMeBee.rest.dto.BeeFamilyRsDto;
import ru.coderiders.BiteMeBee.service.BeeFamilyService;
import ru.coderiders.Library.rest.api.generator.BeeFamilyFeignApi;
import ru.coderiders.Library.rest.dto.GeneratorFamilyRqDto;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeeFamilyController implements BeeFamilyAPI {

    private final BeeFamilyService beeFamilyService;
    private final BeeFamilyFeignApi beeFamilyFeignApi;

    @Override
    public ResponseEntity<BeeFamilyRsDto> create(BeeFamilyRqDto beeFamilyRqDto) {

        BeeFamilyRsDto created = beeFamilyService.create(beeFamilyRqDto);

        GeneratorFamilyRqDto generatorFamilyRqDto = GeneratorFamilyRqDto.builder()
                .id(created.getId())
                .hiveId(beeFamilyRqDto.getHiveId())
                .dronePopulation(created.getDronePopulation())
                .workerPopulation(created.getWorkerPopulation())
                .queenPopulation(created.getQueenPopulation())
                .population(created.getPopulation())
                .diseaseResistance(created.getBeeType().getDiseaseResistance())
                .honeyProductivity(created.getBeeType().getHoneyProductivity())
                .eggProductivity(created.getBeeType().getEggProductivity())
                .build();
        beeFamilyFeignApi.addFamily(generatorFamilyRqDto);

        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @Override
    public BeeFamilyRsDto release(Long id) {
        BeeFamilyRsDto beeFamilyRsDto = beeFamilyService.release(id);
        beeFamilyFeignApi.deleteById(id);

        return beeFamilyRsDto;
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
        beeFamilyFeignApi.deleteById(id);

        return ResponseEntity.accepted().build();
    }
}
