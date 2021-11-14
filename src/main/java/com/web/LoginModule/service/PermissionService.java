package com.web.LoginModule.service;

import com.web.LoginModule.dao.PermissionDao;
import com.web.LoginModule.entity.auth_permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    PermissionDao permissionDao;

    public List<auth_permission> getPermByMenu(String menuid){

        return permissionDao.getPermByMenu(menuid);
    }

}
