package com.api.onlineboard.service.impl;

import com.api.onlineboard.dao.RoleDao;
import com.api.onlineboard.model.Role;
import com.api.onlineboard.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Role save(Role role) {
        return roleDao.save(role);
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.getByRoleName(roleName).orElseThrow();
    }

    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }
}
