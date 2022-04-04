package ru.coderiders.BiteMeBee.rest.api.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.coderiders.BiteMeBee.rest.api.ActivityApi;
import ru.coderiders.BiteMeBee.rest.dto.ActivityRqDto;
import ru.coderiders.BiteMeBee.rest.dto.ActivityRsDto;
import ru.coderiders.BiteMeBee.service.ActivityService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ActivityController implements ActivityApi {
    private final ActivityService activityService;

    @Override
    public Page<ActivityRsDto> getAll(Pageable pageable) {
        return activityService.getAll(pageable);
    }

    @Override
    public ActivityRsDto getById(Long id) {
        return activityService.getById(id);
    }

    @Override
    public ResponseEntity<ActivityRsDto> create(ActivityRqDto activityRqDto) {

        ActivityRsDto created = activityService.create(activityRqDto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }

    @Override
    public ActivityRsDto update(Long id, ActivityRqDto activityRqDto) {
        return activityService.update(id, activityRqDto);
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        activityService.deleteById(id);
        return ResponseEntity.accepted()
                .build();
    }
}
