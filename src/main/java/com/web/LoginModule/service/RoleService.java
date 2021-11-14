package com.web.LoginModule.service;

import com.web.LoginModule.dao.RoleDao;
import com.web.LoginModule.entity.auth_role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleDao roleDao;


    public List<auth_role> rolePageByUser(String userid){
        return roleDao.getRoleByUser(userid);
    }
}
