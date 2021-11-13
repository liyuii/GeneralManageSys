package com.web.dao;

import com.web.entity.Role;

import java.util.List;

public interface RoleDao {

    List<Role> rolePage(String id);

    Role getRoleByName(String name);
}
