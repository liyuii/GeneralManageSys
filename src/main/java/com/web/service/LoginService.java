package com.web.service;

import com.web.entity.Accountant;
import com.web.entity.Function;
import com.web.entity.Loginer;
import com.web.entity.Role;

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
