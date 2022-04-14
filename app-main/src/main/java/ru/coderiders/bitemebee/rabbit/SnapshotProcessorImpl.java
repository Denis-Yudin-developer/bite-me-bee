package ru.coderiders.bitemebee.rabbit;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.bitemebee.entity.HiveSnapshot;
import ru.coderiders.bitemebee.service.HiveService;
import ru.coderiders.bitemebee.service.HiveSnapshotService;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnapshotProcessorImpl implements SnapshotProcessor {
    private final HiveService hiveService;
    private final HiveSnapshotService hiveSnapshotService;

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
}
