package ru.coderiders.bitemebee.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.coderiders.commons.rest.dto.HiveSnapshotGeneratorDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class SnapshotListener {
    private final SnapshotProcessor snapshotProcessor;
    private final String HIVE_SNAPSHOT_QUEUE = "hive-snapshot";

    @RabbitListener(queues = HIVE_SNAPSHOT_QUEUE)
    public void hiveSnapshotListener(HiveSnapshotGeneratorDto hiveSnapshotGeneratorDto) {
        log.debug("Получен снимок улья с hiveId = {}", hiveSnapshotGeneratorDto.getHiveId());
        snapshotProcessor.processHiveSnapshot(hiveSnapshotGeneratorDto);
    }
}
