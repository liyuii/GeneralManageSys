package com.web.LoginModule.service;

import com.web.LoginModule.entity.Function;
import com.web.LoginModule.entity.Loginer;
import com.web.LoginModule.entity.Role;

import java.util.List;

public interface LoginService {

    void getAnnotation();

    List<Loginer> loginerPage(String id);

    List<Role> rolePage(String id);

    List<Function> funPage(String id);

    Loginer getLoginByName(String name);

    Role getRoleByName(String name);

    Function getFunByName(String name);


}
