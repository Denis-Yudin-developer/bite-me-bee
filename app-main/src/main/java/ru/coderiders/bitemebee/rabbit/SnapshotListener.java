package ru.coderiders.bitemebee.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.coderiders.commons.rest.dto.HiveSnapshotRsDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class SnapshotListener {
    private final SnapshotProcessor snapshotProcessor;

    @RabbitListener(queues = "hive-snapshot")
    public void hiveSnapshotListener(HiveSnapshotRsDto hiveSnapshotRsDto) {
        snapshotProcessor.processHiveSnapshot(hiveSnapshotRsDto);
    }
}
