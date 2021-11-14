package com.web.LoginModule.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class auth_user implements Serializable {

    private String userid;
    private String username;
    private String userpassword;
    private String realname;
    private String shbj;
    private String scbj;
    private Date addtime;

    /**
     * 用户对应角色集合
     */
    private List<auth_role> roles;

}
