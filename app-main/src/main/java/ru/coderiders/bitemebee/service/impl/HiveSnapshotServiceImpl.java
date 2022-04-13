package ru.coderiders.bitemebee.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.bitemebee.entity.Hive;
import ru.coderiders.bitemebee.entity.HiveSnapshot;
import ru.coderiders.bitemebee.mapper.HiveSnapshotMapper;
import ru.coderiders.bitemebee.repository.HiveRepository;
import ru.coderiders.bitemebee.repository.HiveSnapshotRepository;
import ru.coderiders.bitemebee.service.HiveSnapshotService;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotRqDto;
import ru.coderiders.commons.rest.exception.NotFoundException;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HiveSnapshotServiceImpl implements HiveSnapshotService {
    private final String HIVE_NOT_FOUND = "Улей с id=%s не найден";
    private final HiveSnapshotRepository hiveSnapshotRepository;
    private final HiveSnapshotMapper hiveSnapshotMapper;
    private final HiveRepository hiveRepository;

    @Override
    @Transactional
    public List<HiveSnapshotDto> getSnapshots(@NonNull Pageable pageable, @NonNull HiveSnapshotRqDto hiveSnapshotRqDto) {
        log.debug("Запрос на получение всех снимков улья за период, hiveSnapshotRqDto = {}", hiveSnapshotRqDto);
        Long hiveId = hiveSnapshotRqDto.getHiveId();
        Instant dateFrom = hiveSnapshotRqDto.getDateFrom();
        Instant dateTo = hiveSnapshotRqDto.getDateTo();
        hiveRepository.findById(hiveId)
                .orElseThrow(() -> new NotFoundException(String.format(HIVE_NOT_FOUND, hiveId)));
        return hiveSnapshotRepository.findByCreatedAtBetweenAndHive_Id(pageable, dateFrom, dateTo, hiveId)
                .stream()
                .map(hiveSnapshotMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HiveSnapshot createSnapshot(@NonNull HiveSnapshotDto hiveSnapshotDto) {
        log.debug("Запрос на создание нового снимка улья, hiveSnapshotDto = {}", hiveSnapshotDto);
        HiveSnapshot hiveSnapshot = hiveSnapshotMapper.toEntity(hiveSnapshotDto);
        Long hiveId = hiveSnapshotDto.getHiveId();
        Hive hive = new Hive(hiveId);
        Instant snapshotTime = Instant.parse(hiveSnapshotDto.getCreatedAt());
        hiveSnapshot.setHive(hive);
        hiveSnapshot.setCreatedAt(snapshotTime);
        return hiveSnapshotRepository.save(hiveSnapshot);
    }
}
