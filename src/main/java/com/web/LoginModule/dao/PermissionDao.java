package com.web.LoginModule.dao;

import com.web.LoginModule.entity.Function;
import com.web.LoginModule.entity.auth_permission;

import java.util.List;

public interface PermissionDao {

//    List<auth_permission> permPage(String id);

    auth_permission getPermByName(String name);

    List<auth_permission> getPermByMenu(String id);
}
