package com.web.LoginModule.serviceImpl;

import com.web.base.annotation.LogAnnotation;
import com.web.LoginModule.dao.FunctionDao;
import com.web.LoginModule.dao.LoginerDao;
import com.web.LoginModule.dao.RoleDao;
import com.web.LoginModule.entity.Function;
import com.web.LoginModule.entity.Loginer;
import com.web.LoginModule.entity.Role;
import com.web.LoginModule.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    LoginerDao loginerDao;

    @Autowired
    RoleDao roleDao;

    @Autowired
    FunctionDao functionDao;


    @Override
    @LogAnnotation
    public void getAnnotation() {
        System.out.println("进入test33");
    }


    @Override
    public List<Loginer> loginerPage(String id) {
        return loginerDao.loginPage(id);
    }

    @Override
    public List<Role> rolePage(String id) {
        return roleDao.rolePage(id);
    }

    @Override
    public List<Function> funPage(String id) {
        return functionDao.funPage(id);
    }

    @Override
    public Loginer getLoginByName(String name) {
        return loginerDao.getLoginByName(name);
    }

    @Override
    public Role getRoleByName(String name) {
        return null;
    }

    @Override
    public Function getFunByName(String name) {
        return null;
    }
}
