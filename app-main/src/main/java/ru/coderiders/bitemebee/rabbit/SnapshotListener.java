package ru.coderiders.bitemebee.rabbit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

@Slf4j
@Component
@RequiredArgsConstructor
public class SnapshotListener {
    private final SnapshotProcessor snapshotProcessor;

    @RabbitListener(queues = "${rabbit.titles.hive-queue:hive-snapshot}")
    public void hiveSnapshotListener(HiveSnapshotDto hiveSnapshotDto) {
        log.debug("Получен снимок улья с hiveId = {}", hiveSnapshotDto.getHiveId());
        snapshotProcessor.processHiveSnapshot(hiveSnapshotDto);
    }

    @RabbitListener(queues = "${rabbit.titles.family-queue:family-snapshot}")
    public void beeFamilySnapshotListener(BeeFamilySnapshotDto beeFamilySnapshotDto){
        log.debug("Получен снимок пчелиной семьи с beeFamilyId = {}", beeFamilySnapshotDto.getFamilyId());
        snapshotProcessor.processBeeFamilySnapshot(beeFamilySnapshotDto);
    }
}
