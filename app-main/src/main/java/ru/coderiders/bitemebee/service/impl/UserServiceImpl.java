package ru.coderiders.BiteMeBee.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.coderiders.BiteMeBee.mapper.UserMapper;
import ru.coderiders.BiteMeBee.repository.UserRepository;
import ru.coderiders.BiteMeBee.rest.dto.UserRqDto;
import ru.coderiders.BiteMeBee.rest.dto.UserRsDto;
import ru.coderiders.BiteMeBee.service.UserService;
import ru.coderiders.commons.rest.exception.NotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final String USER_NOT_FOUND = "Юзер с id=%s не найден";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<UserRsDto> getAll(@NonNull Pageable pageable) {
        log.debug("Запрос на получение пользователей, pageable = {}", pageable);
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Override
    public UserRsDto getById(@NonNull Long id) {
        log.debug("Запрос на получение пользователя по id = {}", id);
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND, id)));
    }

    @Override
    public UserRsDto create(@NonNull UserRqDto userRqDto) {
        log.debug("Запрос на создание нового пользователя, BeeTypeRqDto = {}", userRqDto);
        var username = userRqDto.getUsername();
        //TODO доделать метод
        return null;
    }

    @Override
    public UserRsDto update(@NonNull Long Id, @NonNull UserRqDto userRqDto) {
        return null;
    }

    @Override
    public void deleteById(@NonNull Long Id) {

    }
}
