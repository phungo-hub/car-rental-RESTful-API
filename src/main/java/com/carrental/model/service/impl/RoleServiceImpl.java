package com.carrental.model.service.impl;

import com.carrental.model.entity.Role;
import com.carrental.model.entity.RoleName;
import com.carrental.model.repository.RoleRepository;
import com.carrental.model.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return roleRepository.findByName(name);
    }
}
