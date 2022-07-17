package com.api.onlineboard.service;

import com.api.onlineboard.model.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);

    Role getRoleByName(String roleName);

    List<Role> findAll();
}
