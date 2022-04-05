package ru.coderiders.bitemebee.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/generator_panel")
@Tag(name = "Панель управления ульем", description = "Позволяет управлять состояниями улья и пчелиной семьи")
public interface GenPanelApi {
    @PutMapping("/{id}/change_delta")
    @Operation(description = "Обновить дельту улья", method = "PUT")
    void updateDelta(@PathVariable Long id, @RequestBody Double delta);

    @PutMapping("/{id}/overheat")
    @Operation(description = "Перегреть улей", method = "PUT")
    void overheatHive(@PathVariable Long id);

    @PutMapping("/{id}/infect")
    @Operation(description = "Заразить семью", method = "PUT")
    void infect(@PathVariable Long id);
}
