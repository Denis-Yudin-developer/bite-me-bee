package ru.coderiders.bitemebee.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.coderiders.bitemebee.rest.dto.ScheduleRqDto;
import ru.coderiders.bitemebee.rest.dto.ScheduleRsDto;

import javax.validation.Valid;

@Validated
@RequestMapping("/api")
@Tag(name = "Контроллер расписаний плановых работ", description = "Позволяет управлять расписанием плановых работ")
//@SecurityRequirement(name = "bitemebee")
public interface ScheduleApi {
    @PostMapping("/bee_types/{id}/schedules")
    @Operation(description = "Создание расписания для плановой работы", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ScheduleRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    ResponseEntity<ScheduleRsDto> create(@Parameter(name = "id", description = "Идентификатор вида пчёл", example = "1")
                                         @PathVariable(name = "id") Long beeTypeId,
                                         @Valid @RequestBody ScheduleRqDto scheduleRqDto);

    @GetMapping("/bee_types/{id}/schedules")
    @Operation(description = "Получить расписания всех плановых работ для вида пчёл", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ScheduleRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    Page<ScheduleRsDto> getAll(@Parameter(name = "id", description = "Идентификатор вида пчёл", example = "1")
                               @PathVariable(name = "id") Long beeTypeId,
                               Pageable pageable);

    @PutMapping("/schedules/{id}")
    @Operation(description = "Обновить расписание плановой работы по идентификатору", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ScheduleRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    ScheduleRsDto update(@Parameter(name = "id", description = "Идентификатор расписания плановой работы", example = "1")
                         @PathVariable(name = "id") Long scheduleId,
                         @Valid @RequestBody ScheduleRqDto scheduleRqDto);

    @DeleteMapping("/schedules/{id}")
    @Operation(description = "Удалить расписание плановой работы по идентификатору", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "ACCEPTED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    ResponseEntity<Void> delete(@Parameter(name = "id", description = "Идентификатор расписания плановой работы", example = "1")
                                @PathVariable(name = "id") Long scheduleId);
}
