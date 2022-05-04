package ru.coderiders.bitemebee.visitor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.BeeType;
import ru.coderiders.bitemebee.rest.dto.JobRqDto;
import ru.coderiders.bitemebee.service.JobService;
import ru.coderiders.bitemebee.service.UserService;
import ru.coderiders.commons.rest.dto.HiveSnapshotDto;

@Component
@RequiredArgsConstructor
public class ChilledVisitor implements BeeTypeVisitor {
    private final JobService jobService;
    private final UserService userService;

    @Override
    public void visit(BeeType beeType, HiveSnapshotDto hiveSnapshot) {
        if(beeType.getMinTemperature() > hiveSnapshot.getTemperature()) {
            JobRqDto jobRqDto = JobRqDto.builder()
                    .activityId(5L)
                    .note("Температура в улье ниже необходимой")
                    .hiveId(hiveSnapshot.getHiveId())
                    .userId(userService.getRandomUserId())
                    .build();
            jobService.create(jobRqDto);
        }
    }
}
