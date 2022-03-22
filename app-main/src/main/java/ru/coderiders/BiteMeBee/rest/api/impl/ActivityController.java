package ru.coderiders.BiteMeBee.rest.api.impl;

import ru.coderiders.BiteMeBee.rest.api.ActivityApi;
import ru.coderiders.BiteMeBee.rest.dto.ActivityRqDto;
import ru.coderiders.BiteMeBee.rest.dto.ActivityRsDto;
import ru.coderiders.BiteMeBee.rest.exception.BadRequestException;
import ru.coderiders.BiteMeBee.service.ActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ActivityController implements ActivityApi {

    private final ActivityService activityService;

    private final String ACTIVITY_NOT_CREATED = "Типовая работа не создана";

    @Override
    public Page<ActivityRsDto> getAll(Pageable pageable) {
        log.info("GET ALL /pageable={}", pageable);

        return activityService.getAll(pageable);
    }

    @Override
    public ActivityRsDto getById(Long id) {
        log.info("GET /id={}", id);

        return activityService.getById(id);
    }

    @Override
    public ResponseEntity<ActivityRsDto> create(ActivityRqDto activityRqDto) {
        log.info("CREATE /activityRqDto={}", activityRqDto);

        return Optional.ofNullable(activityService.create(activityRqDto))
                .map(created -> {
                    var url = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(created.getId())
                            .toUri();
                    return ResponseEntity.created(url).body(created);
                })
                .orElseThrow(() -> new BadRequestException(ACTIVITY_NOT_CREATED));
    }

    @Override
    public ActivityRsDto update(Long id, ActivityRqDto activityRqDto) {
        log.info("UPDATE /id={}", id);

        return activityService.update(id, activityRqDto);
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        log.info("DELETE /id={}", id);

        activityService.deleteById(id);
        return ResponseEntity.accepted()
                .build();
    }
}
