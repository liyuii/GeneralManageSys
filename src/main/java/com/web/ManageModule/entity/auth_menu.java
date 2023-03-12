package com.web.ManageModule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class auth_menu {

    private String menuid;
    private String menuname;
    private String menucode;
    private String url;
    private String parentid;
    private String shbj;
    private String scbj;
    private Date adddtime;

    /**
     * 子菜单
     */
    private List<auth_menu> subMenus;

    /**
     * 菜单对应权限集合
     */
    private List<auth_permission> perms;
}
