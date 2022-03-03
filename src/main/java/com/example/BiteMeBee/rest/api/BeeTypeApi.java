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

@RequestMapping("/api/bee_types")
@Tag(name = "BeeType", description = "API вида пчёл")
public interface BeeTypeApi {

    @GetMapping()
    @Operation(summary = "Получить все виды пчёл", tags = "beeType")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = BeeTypeRsDto.class)))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST"
            )
    })
    Page<BeeTypeRsDto> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @Operation(summary = "Получить вид пчелы по id", tags = "beeType")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BeeTypeRsDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND"
                    )
    })
    BeeTypeRsDto getById(@PathVariable(name = "id") Long id);

    @PostMapping
    @Operation(summary = "Добавить вид пчелы", tags = "beeType")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "CREATED",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BeeTypeRsDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST"
                    )
    })
    ResponseEntity<BeeTypeRsDto> create(@Valid @RequestBody BeeTypeRqDto beeTypeRqDto);

    @PutMapping("/{id}")
    @Operation(summary = "Обновить вид пчелы", tags = "beeType")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = BeeTypeRsDto.class))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND"
            )
    })
    BeeTypeRsDto update(@PathVariable Long id, @Valid @RequestBody BeeTypeRqDto beeTypeRqDto);

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить вид пчелы", tags = "beeType")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "202",
                    description = "ACCEPTED"
                    ),
            @ApiResponse(
                    responseCode = "400",
                    description = "BAD REQUEST"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND"
                    )
    })
    ResponseEntity<?> deleteById(@PathVariable(name = "id") Long id);
}
