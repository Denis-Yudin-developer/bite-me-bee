package ru.coderiders.generator.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;
import ru.coderiders.generator.entity.Hive;
import ru.coderiders.generator.service.HiveService;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SnapshotScheduling {
    private final RabbitTemplate template;
    private final HiveService hiveService;
    @Value("${rabbit.titles.hive-queue:hive-snapshot}")
    private String HIVE_SNAPSHOT_QUEUE;

    @Scheduled(fixedDelayString = "${generator.delay:5000}")
    public void produceHiveSnapshot() {
        log.info("Создание новых снимков улья");
        List<Hive> hiveList = hiveService.findAllWithBeeFamilies();
        for (Hive hive : hiveList) {
            HiveSnapshotDto hiveSnapshotRsDto = hiveService.createHiveSnapshot(hive);
            log.debug("Создан новый снимок улья, snapshot = {}", hiveSnapshotRsDto);
            template.convertAndSend(HIVE_SNAPSHOT_QUEUE, hiveSnapshotRsDto);
        }
    }
}
