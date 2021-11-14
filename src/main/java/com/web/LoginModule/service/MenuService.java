package com.web.LoginModule.service;

import com.web.LoginModule.dao.MenuDao;
import com.web.LoginModule.entity.auth_menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuDao menuDao;


    public List<auth_menu> getMenuByRole(String roleid){
        return menuDao.getMenuByRole(roleid);
    }
}
