package com.deutsche.Blog.service.impl;

import com.deutsche.Blog.dao.RoleDao;
import com.deutsche.Blog.model.Role;
import com.deutsche.Blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        return roleDao.findRoleByName(name);
    }
}
