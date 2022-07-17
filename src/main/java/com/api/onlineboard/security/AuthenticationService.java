package com.api.onlineboard.security;

import com.api.onlineboard.dto.UserLoginDto;
import com.api.onlineboard.dto.UserRegistrationDto;
import com.api.onlineboard.model.User;

import javax.naming.AuthenticationException;

public interface AuthenticationService {
    User register(UserRegistrationDto userRegistrationDto);

    User login(UserLoginDto userLoginDto) throws AuthenticationException;
}
