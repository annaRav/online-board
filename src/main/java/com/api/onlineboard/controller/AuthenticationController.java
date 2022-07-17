package com.api.onlineboard.controller;

import com.api.onlineboard.dto.UserLoginDto;
import com.api.onlineboard.dto.UserRegistrationDto;
import com.api.onlineboard.dto.UserResponseDto;
import com.api.onlineboard.model.Role;
import com.api.onlineboard.model.User;
import com.api.onlineboard.security.AuthenticationService;
import com.api.onlineboard.security.jwt.JwtTokenProvider;
import com.api.onlineboard.service.mapper.UserMapper;
import java.util.List;
import java.util.Map;
import javax.naming.AuthenticationException;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public UserResponseDto register(@RequestBody @Valid UserRegistrationDto userDto) {
        User user = authenticationService.register(userDto);
        return userMapper.toResponseDto(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginDto userDto) throws AuthenticationException {
        User user = authenticationService.login(userDto);
        List<String> roles = user.getRoles().stream().map(Role::getRoleName).collect(toList());
        String token = jwtTokenProvider.createToken(user.getEmail(), roles);
        return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
    }
}
