package com.web.LoginModule.service;

import com.web.LoginModule.entity.User;
import com.web.query.UserQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    /*
     *
     *获取用户列表
     */
    List<User> userPage(UserQuery queryCmd);

    /*
     *
     *获取用户列表
     */
    int importUserInfo(MultipartFile file);


    /*
     *
     *通过id获取用户
     */
    User getUserById(String userId);

    /*
     *
     *通过name获取用户
     */
    User getUserByName(String userName);

    /*
     *
     *添加用户
     */
    int addUser(User user);

    /*
     *
     *更新某个用户
     */
    int updUser(User user);


    /*
     *
     *删除某个用户
     */
    int delUser(String uid);
}
