package ru.coderiders.bitemebee.rest.api.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.coderiders.bitemebee.entity.ERole;
import ru.coderiders.bitemebee.entity.Role;
import ru.coderiders.bitemebee.entity.User;
import ru.coderiders.bitemebee.entity.UserDetailsImpl;
import ru.coderiders.bitemebee.repository.RoleRepository;
import ru.coderiders.bitemebee.repository.UserRepository;
import ru.coderiders.bitemebee.rest.api.AuthApi;
import ru.coderiders.bitemebee.rest.dto.JwtDto;
import ru.coderiders.bitemebee.rest.dto.LoginDto;
import ru.coderiders.bitemebee.rest.dto.MessageResponse;
import ru.coderiders.bitemebee.rest.dto.SignupDto;
import ru.coderiders.bitemebee.rest.dto.UserDto;
import ru.coderiders.bitemebee.service.AuthService;
import ru.coderiders.bitemebee.utils.JwtUtils;
import ru.coderiders.commons.rest.exception.BadRequestException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final AuthService authService;

    @Override
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authService.authenticateUser(loginDto));
    }

    @Override
    public void customLogout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
    }

    @Override
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupDto signUpDto) {
        UserDto created = authService.registerUser(signUpDto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }
}
