package ru.coderiders.bitemebee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.Schedule;
import ru.coderiders.bitemebee.repository.ActivityRepository;
import ru.coderiders.bitemebee.rest.dto.ScheduleRqDto;
import ru.coderiders.bitemebee.rest.dto.ScheduleRsDto;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {

    private final ModelMapper modelMapper;
    private final ActivityRepository activityRepository;

    @PostConstruct
    private void init() {
        modelMapper.createTypeMap(ScheduleRqDto.class, Schedule.class)
                .addMappings(mapping -> mapping.skip(Schedule::setBeeType))
                .addMappings(mapping -> mapping.skip(Schedule::setActivity))
                .setPostConverter(toEntityPoseConverter());
        modelMapper.createTypeMap(Schedule.class, ScheduleRsDto.class);
    }

    public Schedule toEntity(ScheduleRqDto scheduleRqDto) {
        return modelMapper.map(scheduleRqDto, Schedule.class);
    }

    public ScheduleRsDto toDto(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleRsDto.class);
    }

    private Converter<ScheduleRqDto, Schedule> toEntityPoseConverter() {
        return context -> {
            var scheduleSrc = context.getSource();
            var scheduleDst = context.getDestination();
            Optional.of(scheduleSrc.getActivityId())
                    .flatMap(activityRepository::findById)
                    .ifPresent(scheduleDst::setActivity);
            return scheduleDst;
        };
    }
}
