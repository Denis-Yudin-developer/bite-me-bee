package ru.coderiders.bitemebee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.bitemebee.entity.User;
import ru.coderiders.bitemebee.rest.dto.UserDto;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    @PostConstruct
    private void init(){
        modelMapper.createTypeMap(User.class, UserDto.class);
    }

    public UserDto toDto(User user){
        return modelMapper.map(user, UserDto.class);
    }
}
