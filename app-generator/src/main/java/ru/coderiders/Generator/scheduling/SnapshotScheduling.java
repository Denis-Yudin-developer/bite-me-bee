package ru.coderiders.Generator.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.coderiders.Generator.client.OpenWeatherFeignClient;
import ru.coderiders.Generator.entity.Hive;
import ru.coderiders.Generator.repository.HiveRepository;
import ru.coderiders.Generator.rest.dto.HiveSnapshotRsDto;
import ru.coderiders.Generator.rest.dto.openweather.WeatherDto;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Component
@EnableScheduling
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
            if(hive.getBeeFamily() == null) continue;

            Instant snapshotTime = Instant.now();

            WeatherDto weatherDto = openWeatherFeignClient.getWeather();

            Double co2 = ThreadLocalRandom.current().nextDouble(100.0, 1000.0);

            Double heatFactor = hive.getIsOverheated() ? 5.0 : 0.0;

            Double temperature = (weatherDto.getMain().getTemp() / 10) + 30 + heatFactor;

            Double healthFactor = hive.getBeeFamily().getIsInfected() ? 0.2 : 1.0;

            Double honeyIncrease =
                    ((hive.getBeeFamily().getHoneyProductivity() * hive.getDelta()) / 100)
                    * ThreadLocalRandom.current().nextDouble(1.0, 2.0) * healthFactor;

            if (hive.getCurrentHoneyAmount() + honeyIncrease > hive.getHoneyCapacity()) {
                honeyIncrease = hive.getHoneyCapacity() - hive.getCurrentHoneyAmount();
            }

            Double currentHoneyAmount = hive.getCurrentHoneyAmount() + honeyIncrease;
            hive.setCurrentHoneyAmount(currentHoneyAmount);
            hiveRepository.save(hive);

            HiveSnapshotRsDto hiveSnapshotRsDto = HiveSnapshotRsDto.builder()
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
