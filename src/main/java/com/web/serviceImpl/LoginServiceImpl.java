package com.web.serviceImpl;

import com.web.annotation.LogAnnotation;
import com.web.dao.FunctionDao;
import com.web.dao.LoginerDao;
import com.web.dao.RoleDao;
import com.web.entity.Function;
import com.web.entity.Loginer;
import com.web.entity.Role;
import com.web.service.LoginService;
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
