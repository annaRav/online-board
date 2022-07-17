package com.api.onlineboard.security;

import com.api.onlineboard.dto.UserLoginDto;
import com.api.onlineboard.dto.UserRegistrationDto;
import com.api.onlineboard.model.User;
import com.api.onlineboard.service.RoleService;
import com.api.onlineboard.service.UserService;
import java.util.Optional;
import java.util.Set;
import javax.naming.AuthenticationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User register(UserRegistrationDto userRegistration) {
        User user = new User();
        user.setEmail(userRegistration.getUsername());
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        user.setRoles(Set.of(roleService.getRoleByName("CONSUMER")));
        user.setStatus(User.Status.ANTIVE);
        user = userService.save(user);
        log.info("IN register - user: {} successfully registered", userRegistration);
        return user;
    }

    @Override
    public User login(UserLoginDto userLogin) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(userLogin.getLogin());
        String encodedPassword = passwordEncoder.encode(userLogin.getPassword());
        if (user.isEmpty() || user.get().getPassword().equals(encodedPassword)) {
            throw new AuthenticationException("Incorrect username or password!!!");
        }
        return user.get();
    }
}
