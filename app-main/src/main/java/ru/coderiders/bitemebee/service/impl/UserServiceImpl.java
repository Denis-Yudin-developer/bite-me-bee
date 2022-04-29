package ru.coderiders.bitemebee.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.coderiders.bitemebee.entity.Role;
import ru.coderiders.bitemebee.mapper.UserMapper;
import ru.coderiders.bitemebee.repository.UserRepository;
import ru.coderiders.bitemebee.rest.dto.UserRqDto;
import ru.coderiders.bitemebee.rest.dto.UserRsDto;
import ru.coderiders.bitemebee.service.UserService;
import ru.coderiders.bitemebee.entity.User;
import ru.coderiders.bitemebee.utils.BeanUtilsHelper;
import ru.coderiders.commons.rest.exception.BadRequestException;
import ru.coderiders.commons.rest.exception.NotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final String[] IGNORED_ON_COPY_FIELDS = {"id"};
    private final String USER_NOT_FOUND = "Пользователь с id=%s не найден";
    private final String USER_ALREADY_EXISTS = "Пользователь с таким именем уже существует";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    @Override
    @Transactional
    public Page<UserRsDto> getAll(@NonNull Pageable pageable) {
        log.debug("Запрос на получение пользователей, pageable = {}", pageable);
        return userRepository.findAll(pageable)
                .map(userMapper::toDto);
    }

    @Override
    @Transactional
    public UserRsDto getById(@NonNull Long id) {
        log.debug("Запрос на получение пользователя по id = {}", id);
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public UserRsDto create(@NonNull UserRqDto userRqDto) {
        log.debug("Запрос на создание нового пользователя, userRqDto = {}", userRqDto);
        if(userRepository.existsByUsername(userRqDto.getUsername())){
            throw new BadRequestException(USER_ALREADY_EXISTS);
        }
        User toCreate = userMapper.toEntity(userRqDto);
        toCreate.setRole(new SimpleGrantedAuthority(Role.USER.getAuthority()));
        toCreate.setPassword(encoder.encode(toCreate.getPassword()));
        User created = userRepository.save(toCreate);
        return userMapper.toDto(created);
    }

    @Override
    @Transactional
    public UserRsDto update(@NonNull Long id, @NonNull UserRqDto userRqDto) {
        log.debug("Запрос на обновление пользователя по id = {}, HiveRqDto = {}", id, userRqDto);
        return userRepository.findById(id)
                .map(found -> {
                    var update = userMapper.toEntity(userRqDto);
                    BeanUtils.copyProperties(update, found,
                            BeanUtilsHelper.getNullPropertyNames(update, IGNORED_ON_COPY_FIELDS));
                    return found;
                })
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND, id)));
    }

    @Override
    @Transactional
    public void deleteById(@NonNull Long id) {
        log.debug("Запрос на удаление пользователя по id = {}", id);
        userRepository.findById(id)
                .ifPresentOrElse(userRepository::delete,
                        () -> {
                            throw new NotFoundException(String.format(USER_NOT_FOUND, id));
                        });
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь с таким именем не найден");
        }
        return user;
    }
}
