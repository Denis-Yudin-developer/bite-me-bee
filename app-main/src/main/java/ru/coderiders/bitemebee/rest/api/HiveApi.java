package ru.coderiders.bitemebee.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.coderiders.bitemebee.rest.dto.HiveRqDto;
import ru.coderiders.bitemebee.rest.dto.HiveRsDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;
import ru.coderiders.commons.rest.dto.HiveSnapshotRqDto;

import javax.validation.Valid;

@RequestMapping("/api/hives")
@Tag(name = "Контроллер улья", description = "Позволяет управлять записями об улье")
public interface HiveApi {
    @PostMapping("/snapshots")
    @Operation(description = "Получить все снимки улья за определенный период", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HiveSnapshotDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    Page<HiveSnapshotDto> getSnapshots(Pageable pageable, @Valid @RequestBody HiveSnapshotRqDto hiveSnapshotRqDto);

    @GetMapping
    @Operation(description = "Получить все ульи", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HiveRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    Page<HiveRsDto> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @Operation(description = "Получить улей по id", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HiveRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    HiveRsDto getById(@PathVariable(name = "id") Long id);

    @PostMapping
    @Operation(description = "Добавить улей", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HiveRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<HiveRsDto> create(@Valid @RequestBody HiveRqDto hiveRqDto);

    @PutMapping("/{id}")
    @Operation(description = "Обновить улей", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HiveRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    HiveRsDto update(@PathVariable Long id, @Valid @RequestBody HiveRqDto hiveRqDto);

    @DeleteMapping("/{id}")
    @Operation(description = "Удалить улей", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "ACCEPTED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id);
}
