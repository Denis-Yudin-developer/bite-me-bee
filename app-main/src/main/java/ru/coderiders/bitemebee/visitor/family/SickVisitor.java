package ru.coderiders.bitemebee.visitor.family;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.rest.dto.JobRqDto;
import ru.coderiders.bitemebee.service.JobService;
import ru.coderiders.bitemebee.service.UserService;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;

@Component
@RequiredArgsConstructor
public class SickVisitor implements BeeFamilySnapshotVisitor {
    private final UserService userService;
    private final JobService jobService;

    @Override
    public void visit(FamilySnapshotVisitorDto visitor, BeeFamilySnapshotDto familySnapshot) {
        if(familySnapshot.getPopulationIncrease() < visitor.getPopulationDecreaseFactor()) {
            JobRqDto jobRqDto = JobRqDto.builder()
                    .activityId(4L)
                    .note("Семья заболела")
                    .hiveId(visitor.getHiveId())
                    .userId(userService.getRandomUserId())
                    .build();
            jobService.create(jobRqDto);
        }
    }
}
