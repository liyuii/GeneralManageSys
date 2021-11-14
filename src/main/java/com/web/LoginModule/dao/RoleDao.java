package com.web.LoginModule.dao;

import com.web.LoginModule.entity.auth_role;

import java.util.List;

public interface RoleDao {

//    List<auth_role> rolePage(String id);

    auth_role getRoleByName(String name);

    List<auth_role> getRoleByUser(String id);
}
