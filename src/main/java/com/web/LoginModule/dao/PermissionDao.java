package com.web.LoginModule.dao;

import com.web.LoginModule.entity.Function;

import java.util.List;

public interface PermissionDao {

    List<Function> funPage(String id);

    Function getFunByName(String name);

}
