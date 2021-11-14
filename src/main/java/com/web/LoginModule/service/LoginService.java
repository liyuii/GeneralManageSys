package com.web.LoginModule.service;

import com.web.base.annotation.LogAnnotation;
import com.web.LoginModule.dao.FunctionDao;
import com.web.LoginModule.dao.LoginerDao;
import com.web.LoginModule.dao.RoleDao;
import com.web.LoginModule.entity.Function;
import com.web.LoginModule.entity.Loginer;
import com.web.LoginModule.entity.auth_role;
import com.web.LoginModule.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoginService {

    @Autowired
    LoginerDao loginerDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    FunctionDao functionDao;


    @LogAnnotation
    public void getAnnotation() {
        System.out.println("进入test33");
    }

    public List<Loginer> loginerPage(String id) {
        return loginerDao.loginPage(id);
    }


//    public List<auth_role> rolePage(String id) {
//        return roleDao.rolePage(id);
//    }


    public List<Function> funPage(String id) {
        return functionDao.funPage(id);
    }


    public Loginer getLoginByName(String name) {
        return loginerDao.getLoginByName(name);
    }


    public auth_role getRoleByName(String name) {
        return null;
    }


    public Function getFunByName(String name) {
        return null;
    }
}
