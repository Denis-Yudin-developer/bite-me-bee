package ru.coderiders.bitemebee.rabbit;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.bitemebee.entity.BeeFamilySnapshot;
import ru.coderiders.bitemebee.entity.BeeType;
import ru.coderiders.bitemebee.entity.HiveSnapshot;
import ru.coderiders.bitemebee.repository.BeeFamilyRepository;
import ru.coderiders.bitemebee.rest.dto.HiveRsDto;
import ru.coderiders.bitemebee.rest.dto.JobRqDto;
import ru.coderiders.bitemebee.service.BeeFamilyService;
import ru.coderiders.bitemebee.service.BeeFamilySnapshotService;
import ru.coderiders.bitemebee.service.HiveService;
import ru.coderiders.bitemebee.service.HiveSnapshotService;
import ru.coderiders.bitemebee.service.JobService;
import ru.coderiders.bitemebee.service.UserService;
import ru.coderiders.bitemebee.visitor.family.FamilySnapshotVisitorDto;
import ru.coderiders.bitemebee.visitor.family.LinkedVisitorFamily;
import ru.coderiders.bitemebee.visitor.hive.HiveSnapshotVisitorDto;
import ru.coderiders.bitemebee.visitor.hive.LinkedVisitorHive;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnapshotProcessorImpl implements SnapshotProcessor {
    private final HiveService hiveService;
    private final UserService userService;
    private final BeeFamilyService beeFamilyService;
    private final JobService jobService;
    private final HiveSnapshotService hiveSnapshotService;
    private final BeeFamilySnapshotService beeFamilySnapshotService;
    private final BeeFamilyRepository beeFamilyRepository;
    private final LinkedVisitorHive linkedVisitorHive;
    private final LinkedVisitorFamily linkedVisitorFamily;

    @Override
    @Transactional
    public void processHiveSnapshot(@NonNull HiveSnapshotDto hiveSnapshot) {
        Long hiveId = hiveSnapshot.getHiveId();
        if (!hiveService.hiveExists(hiveId)) {
            log.warn("Не найден улей по идентификатору, id = {}", hiveId);
            return;
        }
        HiveSnapshot created = hiveSnapshotService.createSnapshot(hiveSnapshot);
        hiveService.updateHoneyAmount(hiveId, created.getHoneyIncrease());
        HiveRsDto hive = hiveService.getById(hiveId);
        beeFamilyRepository.findByHiveIdAndIsAliveTrue(hiveId).ifPresent(beeFamily -> {
            BeeType beeType = beeFamily.getBeeType();
            HiveSnapshotVisitorDto visitorDto = HiveSnapshotVisitorDto.builder()
                    .minTemperature(beeType.getMinTemperature())
                    .maxTemperature(beeType.getMaxTemperature())
                    .honeyCapacity(hive.getFrameCount())
                    .build();
            linkedVisitorHive.visit(visitorDto, hiveSnapshot);
        });
    }

    @Override
    @Transactional
    public void processBeeFamilySnapshot(@NonNull BeeFamilySnapshotDto familySnapshot) {
        Long beeFamilyId = familySnapshot.getFamilyId();
        if(!beeFamilyService.beeFamilyExists(beeFamilyId)) {
            log.warn("Не найдена пчелиная семья по идентификатору, id = {}", beeFamilyId);
            return;
        }
        beeFamilyRepository.findById(beeFamilyId).ifPresent(beeFamily -> {
            Long hiveId = beeFamily.getHive().getId();
            FamilySnapshotVisitorDto visitorDto = FamilySnapshotVisitorDto.builder()
                    .hiveId(hiveId)
                    .populationDecreaseFactor(0)
                    .queenIncreaseFactor(0)
                    .build();
            linkedVisitorFamily.visit(visitorDto, familySnapshot);
        });
        BeeFamilySnapshot beeFamilySnapshot = beeFamilySnapshotService.createSnapshot(familySnapshot);
        beeFamilyService.updatePopulation(beeFamilyId, beeFamilySnapshot.getDronePopulationIncrease(),
                beeFamilySnapshot.getWorkerPopulationIncrease(), beeFamilySnapshot.getQueenPopulationIncrease(),
                beeFamilySnapshot.getPopulationIncrease());
    }
}
