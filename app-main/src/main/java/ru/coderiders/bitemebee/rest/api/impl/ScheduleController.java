package ru.coderiders.bitemebee.rest.api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.coderiders.bitemebee.rest.api.ScheduleApi;
import ru.coderiders.bitemebee.rest.dto.ScheduleRqDto;
import ru.coderiders.bitemebee.rest.dto.ScheduleRsDto;
import ru.coderiders.bitemebee.service.ScheduleService;

@RestController
@RequiredArgsConstructor
public class ScheduleController implements ScheduleApi {
    private final ScheduleService scheduleService;

    @Override
    public ResponseEntity<ScheduleRsDto> create(Long beeTypeId, ScheduleRqDto scheduleRqDto) {
        ScheduleRsDto created = scheduleService.create(beeTypeId, scheduleRqDto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @Override
    public Page<ScheduleRsDto> getAll(Long beeTypeId, Pageable pageable) {
        return scheduleService.getAll(beeTypeId, pageable);
    }

    @Override
    public ScheduleRsDto update(Long scheduleId, ScheduleRqDto scheduleRqDto) {
        return scheduleService.update(scheduleId, scheduleRqDto);
    }

    @Override
    public ResponseEntity<Void> delete(Long scheduleId) {
        scheduleService.deleteById(scheduleId);
        return ResponseEntity.accepted().build();
    }
}
