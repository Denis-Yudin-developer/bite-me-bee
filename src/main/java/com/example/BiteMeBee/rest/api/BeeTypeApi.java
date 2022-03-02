package com.example.BiteMeBee.rest.api;

import com.example.BiteMeBee.rest.dto.BeeTypeRqDto;
import com.example.BiteMeBee.rest.dto.BeeTypeRsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/bee-types")
@Tag(name = "BeeType", description = "API вида пчёл")
public interface BeeTypeApi {

    @GetMapping()
    @Operation(summary = "Получить все виды пчёл", tags = "beeType")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Виды пчёл найдены",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = BeeTypeRsDto.class)))
                    })
    })
    Page<BeeTypeRsDto> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @Operation(summary = "Получить вид пчелы по id", tags = "beeType")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Вид пчелы найден",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BeeTypeRsDto.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Вид пчелы не найден"
                    )
    })
    BeeTypeRsDto getById(@PathVariable(name = "id") Long id);

    @PostMapping
    @Operation(summary = "Добавить вид пчелы", tags = "beeType")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Добавлен вид пчелы",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BeeTypeRsDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Вид пчелы не добавлен"
                    )
    })
    ResponseEntity<BeeTypeRsDto> create(@Valid @RequestBody BeeTypeRqDto beeTypeRqDto);

    @PutMapping("/{id}")
    @Operation(summary = "Обновить вид пчелы", tags = "beeType")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Обновлен вид пчелы",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BeeTypeRsDto.class))
                    }),
            @ApiResponse(
                    responseCode = "404",
                    description = "Не найден вид пчелы, который нужно обновить"
            )
    })
    ResponseEntity<BeeTypeRsDto> update(@Valid @RequestBody BeeTypeRqDto beeTypeRqDto, @PathVariable Long id);

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить вид пчелы", tags = "beeType")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Удалён вид пчелы"
                    ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Не найден вид пчелы, который нужно удалить"
                    )
    })
    ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id);
}
