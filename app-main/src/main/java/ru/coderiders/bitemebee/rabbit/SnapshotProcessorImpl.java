package ru.coderiders.bitemebee.rabbit;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.bitemebee.entity.BeeFamilySnapshot;
import ru.coderiders.bitemebee.entity.HiveSnapshot;
import ru.coderiders.bitemebee.service.BeeFamilyService;
import ru.coderiders.bitemebee.service.BeeFamilySnapshotService;
import ru.coderiders.bitemebee.service.HiveService;
import ru.coderiders.bitemebee.service.HiveSnapshotService;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnapshotProcessorImpl implements SnapshotProcessor {
    private final HiveService hiveService;
    private final BeeFamilyService beeFamilyService;
    private final HiveSnapshotService hiveSnapshotService;
    private final BeeFamilySnapshotService beeFamilySnapshotService;

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
    }

    @Override
    @Transactional
    public void processBeeFamilySnapshot(@NonNull BeeFamilySnapshotDto beeFamilySnapshotGeneratorDto) {
        Long beeFamilyId = beeFamilySnapshotGeneratorDto.getFamilyId();
        if(!beeFamilyService.beeFamilyExists(beeFamilyId)) {
            log.warn("Не найдена пчелиная семья по идентификатору, id = {}", beeFamilyId);
            return;
        }
        BeeFamilySnapshot beeFamilySnapshot = beeFamilySnapshotService.createSnapshot(beeFamilySnapshotGeneratorDto);
        beeFamilyService.updatePopulation(beeFamilyId, beeFamilySnapshot.getDronePopulationIncrease(),
                beeFamilySnapshot.getWorkerPopulationIncrease(), beeFamilySnapshot.getQueenPopulationIncrease(),
                beeFamilySnapshot.getPopulationIncrease());
    }
}
