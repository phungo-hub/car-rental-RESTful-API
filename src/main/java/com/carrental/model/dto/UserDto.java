package com.carrental.model.dto;

import lombok.Data;

import java.util.Set;
@Data
public class UserDto {
    private String name;
    private String username;
    private String password;
    private String email;
    private Set<String> roles;
}
