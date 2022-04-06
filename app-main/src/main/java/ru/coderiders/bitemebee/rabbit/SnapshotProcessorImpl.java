package ru.coderiders.bitemebee.rabbit;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.bitemebee.entity.HiveSnapshot;
import ru.coderiders.bitemebee.service.impl.HiveServiceImpl;
import ru.coderiders.bitemebee.service.impl.HiveSnapshotServiceImpl;
import ru.coderiders.commons.rest.dto.HiveSnapshotRsDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class SnapshotProcessorImpl implements SnapshotProcessor {
    private final HiveServiceImpl hiveService;
    private final HiveSnapshotServiceImpl hiveSnapshotService;

    @Override
    @Transactional
    public void processHiveSnapshot(@NonNull HiveSnapshotRsDto hiveSnapshotRsDto) {
        Long hiveId = hiveSnapshotRsDto.getHiveId();
        if(!hiveService.isExists(hiveId)) {
            log.debug("Не найден улей по идентификатору, id = {}", hiveId);
            return;
        }
        HiveSnapshot hiveSnapshot = hiveSnapshotService.createSnapshot(hiveSnapshotRsDto);
        hiveService.updateHoney(hiveId, hiveSnapshot.getHoneyIncrease());
    }
}
