package ru.coderiders.commons.rest.api.generator;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.coderiders.commons.client.HiveFeignConfig;
import ru.coderiders.commons.rest.dto.GeneratorHiveRqDto;

@FeignClient(name = "hiveFeignApi",
        configuration = HiveFeignConfig.class,
        url = "${feign-client.hives-url}")
public interface HiveFeignApi {
    @PostMapping
    void addHive(@RequestBody GeneratorHiveRqDto generatorHiveRqDto);

    @PutMapping("/{id}/change_delta")
    void updateDelta(@PathVariable Long id, @RequestBody Double delta);

    @PutMapping("/{id}/clear_honey")
    void clearHoney(@PathVariable Long id);

    @PostMapping("/{id}/overheat")
    void updateOverheatedStatus(@PathVariable Long id, @RequestBody Boolean isOverheated);

    @PostMapping("/{id}/chill")
    void updateChilledStatus(@PathVariable Long id, @RequestBody Boolean isChilled);
}
