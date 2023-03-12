package com.web.ManageModule.service;

import com.web.base.annotation.LogAnnotation;
import com.web.ManageModule.dao.FunctionDao;
import com.web.ManageModule.dao.LoginerDao;
import com.web.ManageModule.dao.RoleDao;
import com.web.ManageModule.entity.Function;
import com.web.ManageModule.entity.Loginer;
import com.web.ManageModule.entity.auth_role;
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
