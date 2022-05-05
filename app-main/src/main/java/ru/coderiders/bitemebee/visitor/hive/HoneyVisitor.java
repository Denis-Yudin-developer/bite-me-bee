package ru.coderiders.bitemebee.visitor.hive;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.rest.dto.HiveRsDto;
import ru.coderiders.bitemebee.rest.dto.JobRqDto;
import ru.coderiders.bitemebee.service.HiveService;
import ru.coderiders.bitemebee.service.JobService;
import ru.coderiders.bitemebee.service.UserService;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

@Component
@RequiredArgsConstructor
public class HoneyVisitor implements HiveSnapshotVisitor {
    private final JobService jobService;
    private final HiveService hiveService;
    private final UserService userService;

    @Override
    public void visit(HiveSnapshotVisitorDto visitor, HiveSnapshotDto hiveSnapshot) {
        Long hiveId = hiveSnapshot.getHiveId();
        HiveRsDto hive = hiveService.getById(hiveId);
        if(hive.getHoneyAmount().intValue() == visitor.getHoneyCapacity()) {
            JobRqDto jobRqDto = JobRqDto.builder()
                    .activityId(1L)
                    .note("Улей переполнен мёдом")
                    .hiveId(hiveSnapshot.getHiveId())
                    .userId(userService.getRandomUserId())
                    .build();
            jobService.create(jobRqDto);
        }
    }
}
