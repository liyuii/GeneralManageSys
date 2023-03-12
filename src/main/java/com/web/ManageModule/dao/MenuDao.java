package com.web.ManageModule.dao;

import com.web.ManageModule.entity.auth_menu;

import java.util.List;

public interface MenuDao {

//    List<auth_menu> menuPage(String id);

    auth_menu getMenuByName(String name);

    List<auth_menu> getMenuByRole(String id);

}
