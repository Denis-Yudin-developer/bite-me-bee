package ru.coderiders.bitemebee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.Schedule;
import ru.coderiders.bitemebee.rest.dto.ScheduleRqDto;
import ru.coderiders.bitemebee.rest.dto.ScheduleRsDto;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {
    private final ModelMapper modelMapper;

    @PostConstruct
    private void init() {
        modelMapper.createTypeMap(ScheduleRqDto.class, Schedule.class)
                .addMappings(mapping -> mapping.skip(Schedule::setBeeType))
                .addMappings(mapping -> mapping.skip(Schedule::setActivity));
        modelMapper.createTypeMap(Schedule.class, ScheduleRsDto.class);
    }

    public Schedule toEntity(ScheduleRqDto scheduleRqDto) {
        return modelMapper.map(scheduleRqDto, Schedule.class);
    }

    public ScheduleRsDto toDto(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleRsDto.class);
    }
}
