package com.web.util;

import com.web.ManageModule.entity.auth_user;
import com.web.ManageModule.vo.UserQuery;
import com.web.ManageModule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class whyUtil {

    private static UserService userService;

    public static List<auth_user> methods(){

        UserQuery userQuery = new UserQuery();
        userQuery.setQueryType("0");
        List<auth_user> users = userService.userPage(userQuery);
        return users;
    }

    public static UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        whyUtil.userService = userService;
    }
}
