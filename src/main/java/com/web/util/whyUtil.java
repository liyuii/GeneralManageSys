package com.web.util;

import com.web.LoginModule.entity.User;
import com.web.query.UserQuery;
import com.web.LoginModule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class whyUtil {

    private static UserService userService;

    public static List<User> methods(){

        UserQuery userQuery = new UserQuery();
        userQuery.setQueryType("0");
        List<User> users = userService.userPage(userQuery);
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
