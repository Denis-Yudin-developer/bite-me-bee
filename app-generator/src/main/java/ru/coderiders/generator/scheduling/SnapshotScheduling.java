package ru.coderiders.generator.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.coderiders.commons.client.OpenWeatherFeignClient;
import ru.coderiders.commons.rest.dto.HiveSnapshotRsDto;
import ru.coderiders.commons.rest.dto.openweather.WeatherDto;
import ru.coderiders.generator.entity.Hive;
import ru.coderiders.generator.repository.HiveRepository;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
@RequiredArgsConstructor
public class SnapshotScheduling {
    private final RabbitTemplate template;
    private final HiveRepository hiveRepository;
    private final OpenWeatherFeignClient openWeatherFeignClient;

    @Scheduled(fixedDelay = 5000)
    public void createHiveSnapshot() {
        log.info("Создание новых снимков улья");
        List<Hive> hiveList = hiveRepository.findAll();
        for (Hive hive : hiveList) {
            if (hive.getBeeFamily() == null) continue;
            String snapshotTime = Instant.now().toString();
            WeatherDto weatherDto = openWeatherFeignClient.getWeather();
            Double co2 = ThreadLocalRandom.current().nextDouble(100.0, 1000.0);
            double heatFactor = hive.getIsOverheated() ? 5.0 : 0.0;
            Double temperature = ((weatherDto.getMain().getTemp() / 10) + 30 + heatFactor) ;
            double healthFactor = hive.getBeeFamily().getIsInfected() ? 0.2 : 1.0;
            Double honeyIncrease =
                    ((hive.getBeeFamily().getHoneyProductivity() * hive.getDelta()) / 100)
                            * ThreadLocalRandom.current().nextDouble(1.0, 2.0) * healthFactor;
            if (hive.getCurrentHoneyAmount() + honeyIncrease > hive.getHoneyCapacity()) {
                honeyIncrease = hive.getHoneyCapacity() - hive.getCurrentHoneyAmount();
            }
            Double currentHoneyAmount = hive.getCurrentHoneyAmount() + honeyIncrease;
            hive.setCurrentHoneyAmount(currentHoneyAmount);
            hiveRepository.save(hive);
            var hiveSnapshotRsDto = HiveSnapshotRsDto.builder()
                    .hiveId(hive.getId())
                    .createdAt(snapshotTime)
                    .temperature(temperature)
                    .humidity(weatherDto.getMain().getHumidity())
                    .co2(co2)
                    .honeyIncrease(honeyIncrease).build();
            log.debug("Создан новый снимок улья, snapshot = {}", hiveSnapshotRsDto);
            template.convertAndSend("hive-snapshot", hiveSnapshotRsDto);
        }
    }
}
