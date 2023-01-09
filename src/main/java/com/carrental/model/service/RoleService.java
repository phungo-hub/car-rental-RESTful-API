package com.carrental.model.service;

import com.carrental.model.entity.Role;
import com.carrental.model.entity.RoleName;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName name);
}
