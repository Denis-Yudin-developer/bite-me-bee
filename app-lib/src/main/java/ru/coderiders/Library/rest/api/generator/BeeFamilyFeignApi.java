package ru.coderiders.Library.rest.api.generator;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.coderiders.Library.rest.dto.GeneratorFamilyRqDto;

@FeignClient(name = "beeFamilyFeignApi", url = "http://localhost:8081/api/generator_families")
public interface BeeFamilyFeignApi {

    @PostMapping
    void addFamily(@RequestBody GeneratorFamilyRqDto generatorFamilyRqDto);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable(name = "id") Long id);

    @PostMapping("/{id}/infect")
    void updateInfectedStatus(@PathVariable Long id, @RequestBody Boolean isInfected);
}
