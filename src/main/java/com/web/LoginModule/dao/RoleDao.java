package com.web.LoginModule.dao;

import com.web.LoginModule.entity.Role;

import java.util.List;

public interface RoleDao {

    List<Role> rolePage(String id);

    Role getRoleByName(String name);
}
