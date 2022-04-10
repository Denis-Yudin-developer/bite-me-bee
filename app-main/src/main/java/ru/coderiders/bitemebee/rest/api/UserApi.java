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
import ru.coderiders.bitemebee.entity.User;
import ru.coderiders.bitemebee.rest.dto.HiveRqDto;
import ru.coderiders.bitemebee.rest.dto.HiveRsDto;
import ru.coderiders.bitemebee.rest.dto.UserRqDto;
import ru.coderiders.bitemebee.rest.dto.UserRsDto;

import javax.validation.Valid;

@RequestMapping("/api/users")
@Tag(name = "Контроллер пользователей", description = "Позволяет управлять записями об пользователях")
public interface UserApi {
    @GetMapping
    @Operation(description = "Получить все записи о пользователях", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    Page<UserRsDto> getAll(Pageable pageable);

    @GetMapping("/{id}")
    @Operation(description = "Получить записи о пользователе по id", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    UserRsDto getById(@PathVariable(name = "id") Long id);

    @PostMapping
    @Operation(description = "Добавить записи о пользователе", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    ResponseEntity<UserRsDto> create(@Valid @RequestBody UserRqDto userRqDto);

    @PutMapping("/{id}")
    @Operation(description = "Обновить запись о пользователе", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserRsDto.class))}),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    UserRsDto update(@PathVariable Long id, @Valid @RequestBody UserRqDto userRqDto);

    @DeleteMapping("/{id}")
    @Operation(description = "Удалить запись о пользователе", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "ACCEPTED"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND")
    })
    ResponseEntity<Void> deleteById(@PathVariable(name = "id") Long id);
}
