package ru.coderiders.bitemebee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.User;
import ru.coderiders.bitemebee.rest.dto.UserRqDto;
import ru.coderiders.bitemebee.rest.dto.UserRsDto;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    @PostConstruct
    private void init(){
        modelMapper.createTypeMap(UserRqDto.class, User.class);
        modelMapper.createTypeMap(User.class, UserRsDto.class);
    }

    public User toEntity(UserRqDto userRqDto){
        return modelMapper.map(userRqDto, User.class);
    }

    public UserRsDto toDto(User user){
        return modelMapper.map(user, UserRsDto.class);
    }
}
