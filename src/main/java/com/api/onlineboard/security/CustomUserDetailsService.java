package com.api.onlineboard.security;

import com.api.onlineboard.model.Role;
import com.api.onlineboard.service.UserService;
import com.api.onlineboard.model.User;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userService.findByEmail(email);

        org.springframework.security.core.userdetails.User.UserBuilder builder;
        if (userOptional.isPresent()) {
            builder = org.springframework.security.core.userdetails.User.withUsername(email);
            builder.password(userOptional.get().getPassword());
            builder.roles(userOptional.get().getRoles()
                .stream()
                .map(Role::getRoleName)
                .toArray(String[]::new));
            log.info("IN loadUserByUsername - user: {} successfully loaded", email);
            return builder.build();
        }
        throw new UsernameNotFoundException("User not found.");
    }
}
