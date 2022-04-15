package ru.coderiders.bitemebee.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;
import ru.coderiders.bitemebee.entity.BeeFamily;
import ru.coderiders.bitemebee.entity.BeeFamilySnapshot;
import ru.coderiders.bitemebee.mapper.BeeFamilySnapshotMapper;
import ru.coderiders.bitemebee.repository.BeeFamilyRepository;
import ru.coderiders.bitemebee.repository.BeeFamilySnapshotRepository;
import ru.coderiders.bitemebee.service.BeeFamilySnapshotService;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotRqDto;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BeeFamilySnapshotServiceImpl implements BeeFamilySnapshotService {
    private final String BEE_FAMILY_NOT_FOUND = "Семья с id=%s не найден";
    private final BeeFamilyRepository beeFamilyRepository;
    private final BeeFamilySnapshotRepository beeFamilySnapshotRepository;
    private final BeeFamilySnapshotMapper beeFamilySnapshotMapper;

    @Override
    public List<BeeFamilySnapshotDto> getSnapshots(@NonNull BeeFamilySnapshotRqDto beeFamilySnapshotRqDto) {
        log.debug("Запрос на получение всех снимков пчелиной семьи за период beeFamilySnapshotRqDto = {}",
                beeFamilySnapshotRqDto);
        Long beeFamilyId = beeFamilySnapshotRqDto.getFamilyId();
        Instant dateFrom = beeFamilySnapshotRqDto.getDateFrom();
        Instant dateTo = beeFamilySnapshotRqDto.getDateTo();
        beeFamilyRepository.findById(beeFamilyId)
                .orElseThrow(() -> new NotFoundException(String.format(BEE_FAMILY_NOT_FOUND, beeFamilyId)));
        return beeFamilySnapshotRepository.findByCreatedAtBetweenAndBeeFamily_Id(dateFrom, dateTo, beeFamilyId)
                .stream()
                .map(beeFamilySnapshotMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BeeFamilySnapshot createSnapshot(@NonNull BeeFamilySnapshotDto beeFamilySnapshotGeneratorDto) {
        log.debug("Запрос на создание нового снимка пчелиной семьи, beeFamilySnapshotGeneratorDto = {}",
                beeFamilySnapshotGeneratorDto);
        BeeFamilySnapshot beeFamilySnapshot = beeFamilySnapshotMapper.toEntity(beeFamilySnapshotGeneratorDto);
        Long beeFamilyId = beeFamilySnapshotGeneratorDto.getFamilyId();
        BeeFamily beeFamily = BeeFamily.builder()
                .id(beeFamilyId).build();
        Instant snapshotTime = Instant.parse(beeFamilySnapshotGeneratorDto.getCreatedAt());
        beeFamilySnapshot.setBeeFamily(beeFamily);
        beeFamilySnapshot.setCreatedAt(snapshotTime);
        return beeFamilySnapshotRepository.save(beeFamilySnapshot);
    }
}
