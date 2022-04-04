package ru.coderiders.Generator.rest.api;

import org.springframework.web.bind.annotation.*;
import ru.coderiders.commons.rest.dto.GeneratorHiveRqDto;

@RequestMapping("/api/generator_hives")
public interface HiveApi {
    @PostMapping
    void addHive(@RequestBody GeneratorHiveRqDto generatorHiveRqDto);

    @PutMapping("/{id}/change_delta")
    void updateDelta(@PathVariable Long id, @RequestBody Double delta);

    @PostMapping("/{id}/overheat")
    void updateOverheatedStatus(@PathVariable Long id, @RequestBody Boolean isOverheated);
}
