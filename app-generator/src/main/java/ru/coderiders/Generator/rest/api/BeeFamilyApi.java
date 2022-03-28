package ru.coderiders.Generator.rest.api;

import org.springframework.web.bind.annotation.*;
import ru.coderiders.Library.rest.dto.GeneratorFamilyRqDto;

@RequestMapping("/api/generator_families")
public interface BeeFamilyApi {

    @PostMapping
    void addFamily(@RequestBody GeneratorFamilyRqDto generatorFamilyRqDto);

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable(name = "id") Long id);

    @PostMapping("/{id}/infect")
    void updateInfectedStatus(@PathVariable Long id, @RequestBody Boolean isInfected);
}
