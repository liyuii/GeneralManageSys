package com.web.LoginModule.service;

import com.web.LoginModule.dao.MenuDao;
import com.web.LoginModule.entity.auth_menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    MenuDao menuDao;


    public List<auth_menu> getMenuByRole(String roleid){
        return menuDao.getMenuByRole(roleid);
    }

    //使用递归
    public List<auth_menu> manageMenuTree(List<auth_menu> menus){

        List<auth_menu> trees = new ArrayList<>();

        for(auth_menu menu:menus){
            if("0".equals(menu.getParentid())){
                trees.add(menu);
            }
        }

        for(auth_menu tree:trees){
            tree.setSubMenus(digui(tree, menus));
        }
        return trees;
    }

    private List<auth_menu> digui(auth_menu pmenu,List<auth_menu> menus){

        List<auth_menu> subMenu = new ArrayList<>();

        for(auth_menu menu:menus){
            if(menu.getParentid().equals(pmenu.getMenuid())){
                menu.setSubMenus(digui(menu,menus));
                subMenu.add(menu);
            }
        }
        return subMenu;
    }
}
