package ru.coderiders.commons.rest.api.generator;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.coderiders.commons.config.HiveFeignConfig;
import ru.coderiders.commons.rest.dto.GeneratorHiveRqDto;

@FeignClient(name = "hiveFeignApi",
             configuration = HiveFeignConfig.class,
             url = "${feign-client.families-url:http://localhost:8081/api/generator_hives}")
public interface HiveFeignApi {
    @PostMapping
    void addHive(@RequestBody GeneratorHiveRqDto generatorHiveRqDto);

    @PutMapping("/{id}/change_delta")
    void updateDelta(@PathVariable Long id, @RequestBody Double delta);

    @PostMapping("/{id}/overheat")
    void updateOverheatedStatus(@PathVariable Long id, @RequestBody Boolean isOverheated);
}
