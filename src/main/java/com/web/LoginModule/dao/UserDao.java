package com.web.LoginModule.dao;

import com.web.LoginModule.entity.auth_user;
import com.web.LoginModule.vo.UserQuery;

import java.util.List;

public interface UserDao {

    /*
    *
    *获取用户列表
    */
    List<auth_user> userPage(UserQuery queryCmd);

    /*
     *
     *获取用户列表
     */
    void importUserInfo(auth_user user);

    /*
     *
     *通过id获取用户
     */
    auth_user getUserById(String userId);

    /*
     *
     *通过name获取用户
     */
    auth_user getUserByName(String userName);

    /*
     *
     *添加用户
     */
    int addUser(auth_user user);

    /*
     *
     *更新某个用户
     */
    int updUser(auth_user user);


    /*
     *
     *删除某个用户
     */
    int delUser(String uid);
}
