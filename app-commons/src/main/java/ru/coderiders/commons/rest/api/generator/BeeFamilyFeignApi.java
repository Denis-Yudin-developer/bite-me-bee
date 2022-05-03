package ru.coderiders.commons.rest.api.generator;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.coderiders.commons.client.BeeFamilyFeignConfig;
import ru.coderiders.commons.rest.dto.GeneratorFamilyRqDto;

@FeignClient(name = "beeFamilyFeignApi",
        configuration = BeeFamilyFeignConfig.class,
        url = "${feign-client.families-url}")
public interface BeeFamilyFeignApi {
    @PostMapping
    void addFamily(@RequestBody GeneratorFamilyRqDto generatorFamilyRqDto);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable(name = "id") Long id);

    @PostMapping("/{id}/infect")
    void updateInfectedStatus(@PathVariable Long id, @RequestBody Boolean isInfected);

    @PutMapping("/{id}/change_delta")
    void updateDelta(@PathVariable Long id, @RequestBody Double delta);

    @PutMapping("/{id}/remove_extra_Queen")
    void removeExtraQueen(@PathVariable Long id);
}
