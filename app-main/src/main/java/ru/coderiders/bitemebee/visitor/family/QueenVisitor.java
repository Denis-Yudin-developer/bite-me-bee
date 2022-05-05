package ru.coderiders.bitemebee.visitor.family;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.rest.dto.JobRqDto;
import ru.coderiders.bitemebee.service.JobService;
import ru.coderiders.bitemebee.service.UserService;
import ru.coderiders.commons.rest.dto.BeeFamilySnapshotDto;

@Component
@RequiredArgsConstructor
public class QueenVisitor implements BeeFamilySnapshotVisitor {
    private final UserService userService;
    private final JobService jobService;

    @Override
    public void visit(FamilySnapshotVisitorDto visitor, BeeFamilySnapshotDto familySnapshot) {
        if(familySnapshot.getQueenPopulationIncrease() > visitor.getQueenIncreaseFactor()) {
            JobRqDto jobRqDto = JobRqDto.builder()
                    .activityId(2L)
                    .note("В семья родилась лишняя матка")
                    .hiveId(visitor.getHiveId())
                    .userId(userService.getRandomUserId())
                    .build();
            jobService.create(jobRqDto);
        }
    }
}
