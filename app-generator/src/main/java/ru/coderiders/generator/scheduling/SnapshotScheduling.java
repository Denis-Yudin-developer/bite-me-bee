package ru.coderiders.generator.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.coderiders.commons.rest.dto.HiveSnapshotGeneratorDto;
import ru.coderiders.generator.entity.Hive;
import ru.coderiders.generator.service.HiveService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SnapshotScheduling {
    private final RabbitTemplate template;
    private final HiveService hiveService;
    private final String HIVE_SNAPSHOT_QUEUE = "hive-snapshot";
    private final String DELAY_STRING = "5000";

    @Scheduled(fixedDelayString = DELAY_STRING)
    public void produceHiveSnapshot() {
        log.info("Создание новых снимков улья");
        List<Hive> hiveList = hiveService.findAllWithBeeFamilies();
        for (Hive hive : hiveList) {
            HiveSnapshotGeneratorDto hiveSnapshotRsDto = hiveService.createHiveSnapshot(hive);
            log.debug("Создан новый снимок улья, snapshot = {}", hiveSnapshotRsDto);
            template.convertAndSend(HIVE_SNAPSHOT_QUEUE, hiveSnapshotRsDto);
        }
    }
}
