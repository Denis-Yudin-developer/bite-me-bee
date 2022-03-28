package ru.coderiders.BiteMeBee.rest.api;

import ru.coderiders.BiteMeBee.rest.dto.*;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequestMapping("/api/activities")
@Tag(name = "Контроллер типовых работ", description = "Позволяет управлять записями о типовых видах работ")
public interface ActivityApi {

    @GetMapping
    @Operation(description = "Получить все типовые работы", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActivityRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    Page<ActivityRsDto> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @Operation(description = "Получить типовую работу по id", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActivityRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    ActivityRsDto getById(@PathVariable(name = "id") Long id);

    @PostMapping
    @Operation(description = "Добавить типовую работу", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActivityRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    ResponseEntity<ActivityRsDto> create(@Valid @RequestBody ActivityRqDto activityRqDto);

    @PutMapping("/{id}")
    @Operation(description = "Обновить типовую работу", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ActivityRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    ActivityRsDto update(@PathVariable Long id, @Valid @RequestBody ActivityRqDto activityRqDto);

    @DeleteMapping("/{id}")
    @Operation(description = "Удалить типовую работу", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "ACCEPTED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id);

}
