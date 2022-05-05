package ru.coderiders.generator.rest.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.coderiders.commons.rest.dto.GeneratorHiveRqDto;

@RequestMapping("/api/generator_hives")
public interface HiveApi {
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
