package ru.coderiders.BiteMeBee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.BiteMeBee.entity.Activity;
import ru.coderiders.BiteMeBee.rest.dto.ActivityRqDto;
import ru.coderiders.BiteMeBee.rest.dto.ActivityRsDto;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ActivityMapper {
    private final ModelMapper modelMapper;

    @PostConstruct
    private void init() {
        modelMapper.createTypeMap(ActivityRqDto.class, Activity.class);
        modelMapper.createTypeMap(Activity.class, ActivityRsDto.class);
    }

    public Activity toEntity(ActivityRqDto activityRqDto) {
        return modelMapper.map(activityRqDto, Activity.class);
    }

    public ActivityRsDto toDto(Activity activity) {
        return modelMapper.map(activity, ActivityRsDto.class);
    }
}
