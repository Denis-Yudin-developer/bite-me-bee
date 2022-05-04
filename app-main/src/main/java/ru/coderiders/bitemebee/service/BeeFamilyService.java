package ru.coderiders.bitemebee.service;

import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.coderiders.bitemebee.entity.BeeFamily;
import ru.coderiders.bitemebee.rest.dto.BeeFamilyNoteRqDto;
import ru.coderiders.bitemebee.rest.dto.BeeFamilyRqDto;
import ru.coderiders.bitemebee.rest.dto.BeeFamilyRsDto;

import java.util.List;

public interface BeeFamilyService {
    BeeFamilyRsDto create(@NonNull BeeFamilyRqDto beeFamilyRqDto);

    BeeFamilyRsDto getById(@NonNull Long id);

    Page<BeeFamilyRsDto> getAll(@NonNull Pageable pageable);

    BeeFamilyRsDto update(@NonNull Long id, @NonNull BeeFamilyNoteRqDto beeFamilyNoteRqDto);

    void updatePopulation(@NonNull Long id, @NonNull Long dronePopulation,
                          @NonNull Long workerPopulation, @NonNull Long queenPopulation,
                          @NonNull Long population);

    void release(@NonNull Long id);

    boolean beeFamilyExists(@NonNull Long id);

    void deleteByBeeType(@NonNull Long beeTypeId);

    List<BeeFamily> getAllEntities();
}
