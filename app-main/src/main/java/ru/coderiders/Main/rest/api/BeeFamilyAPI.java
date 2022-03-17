package ru.coderiders.Main.rest.api;

import ru.coderiders.Main.rest.dto.BeeFamilyNoteRqDto;
import ru.coderiders.Main.rest.dto.BeeFamilyRqDto;
import ru.coderiders.Main.rest.dto.BeeFamilyRsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@RequestMapping("/api/bee_families")
@Tag(name = "Контроллер пчелиной семьи", description = "Позволяет управлять записями о пчелиных семьях")
public interface BeeFamilyAPI {

    @PostMapping
    @Operation(description = "Создание записи о пчелиной семье", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BeeFamilyRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    ResponseEntity<BeeFamilyRsDto> create(@Valid @RequestBody BeeFamilyRqDto beeFamilyRqDto);

    @PostMapping("/{id}/release")
    @Operation(description = "\"Выселение\" пчелиной семьи из улья по её идентификатору", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BeeFamilyRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    BeeFamilyRsDto release(@Parameter(required = true, description = "Идентификатор") @PathVariable(name = "id") Long id);

    @GetMapping("/{id}")
    @Operation(description = "Получение записи о пчелиной семье по идентификатору", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BeeFamilyRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    BeeFamilyRsDto getById(@Parameter(required = true, description = "Идентификатор") @PathVariable(name = "id") Long id);

    @GetMapping
    @Operation(description = "Получение всех записей о пчелиных семьях", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    Page<BeeFamilyRsDto> getAll(Pageable pageable);

    @PutMapping("/{id}")
    @Operation(description = "Изменение заметки о пчелиной семье по идентификатору", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BeeFamilyRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    BeeFamilyRsDto update(@Parameter(required = true, description = "Идентификатор") @PathVariable(name = "id") Long id,
                          @Valid @RequestBody BeeFamilyNoteRqDto beeFamilyNoteRqDto);

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление записи о пчелиной семье по идентификатору", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "ACCEPTED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    ResponseEntity<?> delete(@Parameter(required = true, description = "Идентификатор") @PathVariable(name = "id") Long id);
}
