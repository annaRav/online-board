package com.api.onlineboard.service;

import java.util.List;
import java.util.Optional;
import com.api.onlineboard.model.User;

public interface UserService {
    User save(User user);

    User findById(Long id);

    Optional<User> findByEmail(String email);

    void delete(User user);

    List<User> findAll();
}
