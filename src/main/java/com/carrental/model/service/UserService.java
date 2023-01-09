package com.carrental.model.service;

import com.carrental.model.entity.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    Iterable<User> findUsersByNameContaining(String user_name);
    User save(User user);
}
