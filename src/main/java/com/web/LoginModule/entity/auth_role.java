package com.web.LoginModule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class auth_role {

    private String roleid;
    private String rolename;
    private String rolecode;
    private String shbj;
    private String scbj;
    private Date addtime;

    /**
     * 角色对应菜单集合
     */
    private List<auth_menu> menus;

}
