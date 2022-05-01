package ru.coderiders.bitemebee.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.coderiders.bitemebee.entity.ERole;
import ru.coderiders.bitemebee.entity.Role;
import ru.coderiders.bitemebee.entity.User;
import ru.coderiders.bitemebee.entity.UserDetailsImpl;
import ru.coderiders.bitemebee.mapper.UserMapper;
import ru.coderiders.bitemebee.repository.RoleRepository;
import ru.coderiders.bitemebee.repository.UserRepository;
import ru.coderiders.bitemebee.rest.dto.JwtDto;
import ru.coderiders.bitemebee.rest.dto.LoginDto;
import ru.coderiders.bitemebee.rest.dto.SignupDto;
import ru.coderiders.bitemebee.rest.dto.UserDto;
import ru.coderiders.bitemebee.service.AuthService;
import ru.coderiders.bitemebee.utils.JwtUtils;
import ru.coderiders.commons.rest.exception.BadRequestException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final String USERNAME_ALREADY_EXISTS = "Имя пользователя уже занято";
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final UserMapper userMapper;

    @Override
    public JwtDto authenticateUser(@NonNull LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return new JwtDto(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles);
    }

    @Override
    public UserDto registerUser(@NonNull SignupDto signUpDto) {
        if (userRepository.existsByUsername(signUpDto.getUsername())) {
            throw new BadRequestException(USERNAME_ALREADY_EXISTS);
        }
        User user = User.builder()
                .username(signUpDto.getUsername())
                .password(encoder.encode(signUpDto.getPassword()))
                .build();
        Set<Role> roles = new HashSet<>();
        if(roleRepository.findAll().size() == 0) {
            Role roleUser = Role.builder()
                    .name(ERole.ROLE_USER)
                    .build();
            roleRepository.save(roleUser);
            Role roleAdmin = Role.builder()
                    .name(ERole.ROLE_ADMIN)
                    .build();
            roleRepository.save(roleAdmin);
        }
        Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
        roles.add(userRole);
        user.setRoles(roles);
        return userMapper.toDto(userRepository.save(user));
    }
}
