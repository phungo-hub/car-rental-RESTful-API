package com.carrental.model.service;

import com.carrental.model.dto.CarDto;
import com.carrental.model.dto.UserDto;
import com.carrental.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    Iterable<UserDto> findUsersByNameContaining(String user_name);
    User save(UserDto userDto);

    Iterable<UserDto> findAll();

    void remove(Long id);

    Optional<UserDto> findById(Long id);
}
