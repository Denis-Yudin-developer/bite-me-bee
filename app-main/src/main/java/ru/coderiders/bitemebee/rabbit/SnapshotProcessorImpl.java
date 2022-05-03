package ru.coderiders.bitemebee.rabbit;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.bitemebee.entity.BeeFamilySnapshot;
import ru.coderiders.bitemebee.entity.BeeType;
import ru.coderiders.bitemebee.entity.Hive;
import ru.coderiders.bitemebee.entity.HiveSnapshot;
import ru.coderiders.bitemebee.repository.BeeFamilyRepository;
import ru.coderiders.bitemebee.repository.HiveRepository;
import ru.coderiders.bitemebee.rest.dto.HiveRsDto;
import ru.coderiders.bitemebee.rest.dto.JobRqDto;
import ru.coderiders.bitemebee.service.BeeFamilyService;
import ru.coderiders.bitemebee.service.BeeFamilySnapshotService;
import ru.coderiders.bitemebee.service.HiveService;
import ru.coderiders.bitemebee.service.HiveSnapshotService;
import ru.coderiders.bitemebee.service.JobService;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnapshotProcessorImpl implements SnapshotProcessor {
    private final HiveService hiveService;
    private final BeeFamilyService beeFamilyService;
    private final JobService jobService;
    private final HiveSnapshotService hiveSnapshotService;
    private final BeeFamilySnapshotService beeFamilySnapshotService;

    private final BeeFamilyRepository beeFamilyRepository;

    @Override
    @Transactional
    public void processHiveSnapshot(@NonNull HiveSnapshotDto hiveSnapshot) {
        Long hiveId = hiveSnapshot.getHiveId();
        if (!hiveService.hiveExists(hiveId)) {
            log.warn("Не найден улей по идентификатору, id = {}", hiveId);
            return;
        }
        beeFamilyRepository.findByHiveIdAndIsAliveTrue(hiveId).ifPresent(beeFamily -> {
            BeeType beeType = beeFamily.getBeeType();
            if(beeType.getMaxTemperature() < hiveSnapshot.getTemperature()) {
                JobRqDto jobRqDto = JobRqDto.builder()
                        .activityId(3L)
                        .note("Улей перегрет")
                        .hiveId(hiveSnapshot.getHiveId())
                        .createdAt(Instant.now())
                        .build();
                jobService.create(jobRqDto);
            }
        });
        HiveSnapshot created = hiveSnapshotService.createSnapshot(hiveSnapshot);
        hiveService.updateHoneyAmount(hiveId, created.getHoneyIncrease());
        HiveRsDto hive = hiveService.getById(hiveId);
        if(hive.getHoneyAmount() == hive.getFrameCount().doubleValue()) {
            JobRqDto jobRqDto = JobRqDto.builder()
                    .activityId(1L)
                    .note("Улей переполнен мёдом")
                    .hiveId(hiveSnapshot.getHiveId())
                    .createdAt(Instant.now())
                    .build();
            jobService.create(jobRqDto);
        }
    }

    @Override
    @Transactional
    public void processBeeFamilySnapshot(@NonNull BeeFamilySnapshotDto beeFamilySnapshotGeneratorDto) {
        Long beeFamilyId = beeFamilySnapshotGeneratorDto.getFamilyId();
        if(!beeFamilyService.beeFamilyExists(beeFamilyId)) {
            log.warn("Не найдена пчелиная семья по идентификатору, id = {}", beeFamilyId);
            return;
        }
        beeFamilyRepository.findById(beeFamilyId).ifPresent(beeFamily -> {
            Long hiveId = beeFamily.getHive().getId();
            if(beeFamilySnapshotGeneratorDto.getPopulationIncrease() < 0) {
                JobRqDto jobRqDto = JobRqDto.builder()
                        .activityId(4L)
                        .note("Семья заболела")
                        .hiveId(hiveId)
                        .createdAt(Instant.now())
                        .build();
                jobService.create(jobRqDto);
            }
            if(beeFamilySnapshotGeneratorDto.getQueenPopulationIncrease() > 0) {
                JobRqDto jobRqDto = JobRqDto.builder()
                        .activityId(2L)
                        .note("В семья родилась лишняя матка")
                        .hiveId(hiveId)
                        .createdAt(Instant.now())
                        .build();
                jobService.create(jobRqDto);
            }
        });
        BeeFamilySnapshot beeFamilySnapshot = beeFamilySnapshotService.createSnapshot(beeFamilySnapshotGeneratorDto);
        beeFamilyService.updatePopulation(beeFamilyId, beeFamilySnapshot.getDronePopulationIncrease(),
                beeFamilySnapshot.getWorkerPopulationIncrease(), beeFamilySnapshot.getQueenPopulationIncrease(),
                beeFamilySnapshot.getPopulationIncrease());
    }
}
