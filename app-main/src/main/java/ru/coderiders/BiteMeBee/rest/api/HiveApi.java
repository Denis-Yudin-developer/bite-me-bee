package ru.coderiders.BiteMeBee.rest.api;

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
import org.springframework.web.bind.annotation.*;
import ru.coderiders.BiteMeBee.rest.dto.HiveRqDto;
import ru.coderiders.BiteMeBee.rest.dto.HiveRsDto;
import ru.coderiders.Library.rest.dto.HiveSnapshotRqDto;
import ru.coderiders.Library.rest.dto.HiveSnapshotRsDto;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/hives")
@Tag(name = "Контроллер улья", description = "Позволяет управлять записями об улье")
public interface HiveApi {

    @PostMapping("/snapshots")
    @Operation(description = "Получить все снимки улья за определенный период", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HiveSnapshotRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    List<HiveSnapshotRsDto> getSnapshots(@Valid @RequestBody HiveSnapshotRqDto hiveSnapshotRqDto);

    @GetMapping
    @Operation(description = "Получить все ульи", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HiveRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    Page<HiveRsDto> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @Operation(description = "Получить улей по id", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HiveRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    HiveRsDto getById(@PathVariable(name = "id") Long id);

    @PostMapping
    @Operation(description = "Добавить улей", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HiveRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    ResponseEntity<HiveRsDto> create(@Valid @RequestBody HiveRqDto hiveRqDto);

    @PutMapping("/{id}")
    @Operation(description = "Обновить улей", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = HiveRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    HiveRsDto update(@PathVariable Long id, @Valid @RequestBody HiveRqDto hiveRqDto);

    @DeleteMapping("/{id}")
    @Operation(description = "Удалить улей", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "ACCEPTED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id);
}
