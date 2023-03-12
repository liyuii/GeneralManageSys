package com.web.ManageModule.service;

import com.web.ManageModule.dao.RoleDao;
import com.web.ManageModule.entity.auth_role;
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
