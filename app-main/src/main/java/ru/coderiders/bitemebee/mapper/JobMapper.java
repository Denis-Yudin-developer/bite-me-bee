package ru.coderiders.bitemebee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.Job;
import ru.coderiders.bitemebee.repository.ActivityRepository;
import ru.coderiders.bitemebee.repository.HiveRepository;
import ru.coderiders.bitemebee.repository.UserRepository;
import ru.coderiders.bitemebee.rest.dto.JobRqDto;
import ru.coderiders.bitemebee.rest.dto.JobRsDto;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JobMapper {
    private final ModelMapper modelMapper;
    private final ActivityRepository activityRepository;
    private final HiveRepository hiveRepository;
    private final UserRepository userRepository;

    @PostConstruct
    private void init() {
        modelMapper.createTypeMap(JobRqDto.class, Job.class)
                .addMappings(mapping -> mapping.skip(Job::setActivity))
                .addMappings(mapping -> mapping.skip(Job::setHive))
                .addMappings(mapping -> mapping.skip(Job::setUser))
                .setPostConverter(toEntityPostConverter());
        modelMapper.createTypeMap(Job.class, JobRsDto.class)
                .addMappings(mapping -> mapping.skip(JobRsDto::setHive))
                .setPostConverter(toDtoPostConverter());
    }

    public Job toEntity(JobRqDto jobRqDto){
        return modelMapper.map(jobRqDto, Job.class);
    }

    public JobRsDto toDto(Job job){
        return modelMapper.map(job, JobRsDto.class);
    }

    private Converter<JobRqDto, Job> toEntityPostConverter() {
        return context -> {
            var jobSrc = context.getSource();
            var jobDst = context.getDestination();

            Optional.of(jobSrc.getActivityId())
                    .flatMap(activityRepository::findById)
                    .ifPresent(jobDst::setActivity);

            Optional.of(jobSrc.getHiveId())
                    .flatMap(hiveRepository::findById)
                    .ifPresent(jobDst::setHive);

            Optional.of(jobSrc.getUserId())
                    .flatMap(userRepository::findById)
                    .ifPresent(jobDst::setUser);

            return jobDst;
        };
    }

    private Converter<Job, JobRsDto> toDtoPostConverter() {
        return context -> {
            var jobSrc = context.getSource();
            var jobDst = context.getDestination();

            Optional.of(jobSrc.getHive().getId())
                    .ifPresent(jobDst::setHive);

            return  jobDst;
        };
    }
}
