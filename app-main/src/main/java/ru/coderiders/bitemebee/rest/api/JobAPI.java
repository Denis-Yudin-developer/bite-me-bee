package ru.coderiders.bitemebee.rest.api;

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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.coderiders.bitemebee.rest.dto.JobNoteRqDto;
import ru.coderiders.bitemebee.rest.dto.JobRqDto;
import ru.coderiders.bitemebee.rest.dto.JobRsDto;

import javax.validation.Valid;

@Validated
@RequestMapping("/api/jobs")
@Tag(name = "Контроллер работ", description = "Позволяет управлять записями о работах на пасеке")
public interface JobAPI {
    @PostMapping
    @Operation(description = "Создание записи о работе", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED",
                content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = JobRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<JobRsDto> create(@Valid @RequestBody JobRqDto jobRqDto);

    @GetMapping("/{id}")
    @Operation(description = "Получение записи о работе по идентификатору", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = JobRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    JobRsDto getById(@Parameter(required = true, description = "Идентификатор") @PathVariable(name = "id") Long id);

    @GetMapping
    @Operation(description = "Получение всех записей о работе", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    Page<JobRsDto> getAll(Pageable pageable);

    @PutMapping("/{id}")
    @Operation(description = "Изменение заметки о работе по идентификатору", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = JobRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    JobRsDto update(@Parameter(required = true, description = "Идентификатор")
                    @PathVariable(name = "id") Long id,
                    @Valid @RequestBody JobNoteRqDto jobNoteRqDto);

    @PutMapping("/{id}/user")
    @Operation(description = "Изменение исполнителя работы по идентификатору по идентификатору", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = JobRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    JobRsDto assignUser(@Parameter(required = true, description = "Идентификатор")
                        @PathVariable(name = "id") Long id,
                        @Valid @RequestBody Long userId);

    @DeleteMapping("/{id}")
    @Operation(description = "Удаление записи о работе по идентификатору", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "ACCEPTED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    ResponseEntity<Void> complete(@Parameter(required = true, description = "Идентификатор")
                                  @PathVariable(name = "id") Long id);
}
