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

    @RabbitListener(queues = "hive-snapshot")
    public void hiveSnapshotListener(HiveSnapshotGeneratorDto hiveSnapshotGeneratorDto) {
        snapshotProcessor.processHiveSnapshot(hiveSnapshotGeneratorDto);
    }
}
