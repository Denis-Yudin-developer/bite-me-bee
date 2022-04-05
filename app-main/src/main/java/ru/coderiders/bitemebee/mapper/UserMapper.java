package ru.coderiders.BiteMeBee.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.coderiders.BiteMeBee.entity.User;
import ru.coderiders.BiteMeBee.rest.dto.UserRqDto;
import ru.coderiders.BiteMeBee.rest.dto.UserRsDto;

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
