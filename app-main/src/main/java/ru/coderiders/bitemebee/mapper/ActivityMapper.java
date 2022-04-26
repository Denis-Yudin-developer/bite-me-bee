package ru.coderiders.bitemebee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.Activity;
import ru.coderiders.bitemebee.rest.dto.ActivityRqDto;
import ru.coderiders.bitemebee.rest.dto.ActivityRsDto;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class ActivityMapper {
    private final ModelMapper modelMapper;

    @PostConstruct
    private void init() {
        modelMapper.createTypeMap(ActivityRqDto.class, Activity.class)
                .addMappings(mapping -> mapping.skip(Activity::setIsPlanned))
                .setPostConverter(toEntityPostConverter());
        modelMapper.createTypeMap(Activity.class, ActivityRsDto.class);
    }

    public Activity toEntity(ActivityRqDto activityRqDto) {
        return modelMapper.map(activityRqDto, Activity.class);
    }

    public ActivityRsDto toDto(Activity activity) {
        return modelMapper.map(activity, ActivityRsDto.class);
    }

    private Converter<ActivityRqDto, Activity> toEntityPostConverter() {
        return context -> {
            var src = context.getSource();
            var dst = context.getDestination();

            String isPlanned = src.getIsPlanned();
            dst.setIsPlanned(Boolean.parseBoolean(isPlanned));
            return dst;
        };
    }
}
