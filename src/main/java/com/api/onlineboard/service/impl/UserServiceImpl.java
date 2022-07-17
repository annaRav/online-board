package com.api.onlineboard.service.impl;

import com.api.onlineboard.dao.UserDao;
import com.api.onlineboard.model.User;
import com.api.onlineboard.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

    @Override
    public User findById(Long id) {
        Optional<User> optional = userDao.findById(id);
        if (optional.isEmpty()) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }
        log.warn("IN findById - user: {} found by id: {}", optional.get(), optional.get().getId());
        return optional.get();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
        log.info("IN delete - user with id: {} successfully", user.getId());
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
