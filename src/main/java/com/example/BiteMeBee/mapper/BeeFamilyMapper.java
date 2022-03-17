package com.example.BiteMeBee.mapper;

import com.example.BiteMeBee.entity.BeeFamily;
import com.example.BiteMeBee.repository.BeeTypeRepository;
import com.example.BiteMeBee.repository.HiveRepository;
import com.example.BiteMeBee.rest.dto.BeeFamilyRqDto;
import com.example.BiteMeBee.rest.dto.BeeFamilyRsDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BeeFamilyMapper {

    private final ModelMapper modelMapper;
    private final BeeTypeRepository beeTypeRepository;
    private final HiveRepository hiveRepository;

    @PostConstruct
    private void init() {
        modelMapper.createTypeMap(BeeFamilyRqDto.class, BeeFamily.class)
                .addMappings(mapping -> mapping.skip(BeeFamily::setBeeType))
                .addMappings(mapping -> mapping.skip(BeeFamily::setHive))
                .addMappings(mapping -> mapping.skip(BeeFamily::setPopulation))
                .setPostConverter(toEntityPostConverter());
        modelMapper.createTypeMap(BeeFamily.class, BeeFamilyRsDto.class);
    }

    public BeeFamily toEntity(BeeFamilyRqDto beeFamilyRqDto) {
        return modelMapper.map(beeFamilyRqDto, BeeFamily.class);
    }

    public BeeFamilyRsDto toDto(BeeFamily beeFamily) {
        return modelMapper.map(beeFamily, BeeFamilyRsDto.class);
    }

    private Converter<BeeFamilyRqDto, BeeFamily> toEntityPostConverter() {
        return context -> {
            var familySrc = context.getSource();
            var familyDst = context.getDestination();

            Optional.of(familySrc.getBeeTypeId())
                    .flatMap(beeTypeRepository::findById)
                    .ifPresent(familyDst::setBeeType);

            Optional.of(familySrc.getHiveId())
                    .flatMap(hiveRepository::findById)
                    .ifPresent(familyDst::setHive);

            return familyDst;
        };
    }

}
