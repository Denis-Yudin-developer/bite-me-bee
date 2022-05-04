package ru.coderiders.bitemebee.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.bitemebee.entity.User;
import ru.coderiders.bitemebee.entity.UserDetailsImpl;
import ru.coderiders.bitemebee.mapper.UserMapper;
import ru.coderiders.bitemebee.repository.UserRepository;
import ru.coderiders.bitemebee.rest.dto.UserDto;
import ru.coderiders.bitemebee.service.UserService;
import ru.coderiders.commons.rest.exception.NotFoundException;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final String USER_ID_NOT_FOUND = "Пользователь с id=%s не найден";
    private final String USERNAME_NOT_FOUND = "Пользователь с таким именем не найден";
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public Page<UserDto> getAll(@NonNull Pageable pageable) {
        log.debug("Запрос на получение пользователей, pageable = {}", pageable);
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Override
    @Transactional
    public UserDto getById(@NonNull Long id) {
        log.debug("Запрос на получение пользователя по id = {}", id);
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(USER_ID_NOT_FOUND, id)));
    }

    @Override
    public Long getRandomUserId() {
        Random random = new Random();
        return random.nextLong(userRepository.findAll().size()) + 1;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND));
        return UserDetailsImpl.build(user);
    }
}
