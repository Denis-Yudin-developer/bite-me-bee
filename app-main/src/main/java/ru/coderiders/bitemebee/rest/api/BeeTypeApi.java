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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.coderiders.bitemebee.rest.dto.BeeTypeRqDto;
import ru.coderiders.bitemebee.rest.dto.BeeTypeRsDto;

import javax.validation.Valid;

@RequestMapping("/api/bee_types")
@Tag(name = "Контроллер вида пчёл", description = "Позволяет управлять записями о пчелиных видах")
public interface BeeTypeApi {
    @GetMapping
    @Operation(description = "Получить все виды пчёл", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BeeTypeRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    Page<BeeTypeRsDto> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @Operation(description = "Получить вид пчелы по id", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BeeTypeRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    BeeTypeRsDto getById(@PathVariable(name = "id") Long id);

    @PostMapping
    @Operation(description = "Добавить вид пчелы", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BeeTypeRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    ResponseEntity<BeeTypeRsDto> create(@Valid @RequestBody BeeTypeRqDto beeTypeRqDto);

    @PutMapping("/{id}")
    @Operation(description = "Обновить вид пчелы", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = BeeTypeRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    BeeTypeRsDto update(@PathVariable Long id, @Valid @RequestBody BeeTypeRqDto beeTypeRqDto);

    @DeleteMapping("/{id}")
    @Operation(description = "Удалить вид пчелы", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "ACCEPTED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id);
}
