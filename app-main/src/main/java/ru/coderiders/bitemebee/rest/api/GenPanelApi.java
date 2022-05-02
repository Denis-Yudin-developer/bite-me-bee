package ru.coderiders.bitemebee.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/generator_panel")
@Tag(name = "Панель управления ульем", description = "Позволяет управлять состояниями улья и пчелиной семьи")
public interface GenPanelApi {
    @PutMapping("/{id}/change_hive_delta")
    @Operation(description = "Обновить дельту улья", method = "PUT")
    @PreAuthorize("hasRole('ADMIN')")
    void updateHiveDelta(@PathVariable Long id, @RequestBody Double delta);

    @PutMapping("/{id}/change_family_delta")
    @Operation(description = "Обновить дельту пчелиной семьи", method = "PUT")
    @PreAuthorize("hasRole('ADMIN')")
    void updateBeeFamilyDelta(@PathVariable Long id, @RequestBody Double delta);

    @PutMapping("/{id}/overheat")
    @Operation(description = "Перегреть улей", method = "PUT")
    @PreAuthorize("hasRole('ADMIN')")
    void overheatHive(@PathVariable Long id);

    @PutMapping("/{id}/infect")
    @Operation(description = "Заразить семью", method = "PUT")
    @PreAuthorize("hasRole('ADMIN')")
    void infect(@PathVariable Long id);
}
