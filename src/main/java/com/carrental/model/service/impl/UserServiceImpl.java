package com.carrental.model.service.impl;

import com.carrental.model.entity.User;
import com.carrental.model.repository.UserRepository;
import com.carrental.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Iterable<User> findUsersByNameContaining(String user_name) {
        return userRepository.findUsersByNameContaining(user_name);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
