package com.web.LoginModule.controller;

import com.web.base.common.Result;
import com.web.LoginModule.entity.auth_user;
import com.web.base.common.WxProperties;
import com.web.util.random.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.web.util.whyUtil;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mytest")
public class TestController {

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 测试在工具类中获取bean
     * @param
     * @return
     */
    @RequestMapping("/util")
    @ResponseBody
    public void testUtil(){
//        UserQuery userQuery = new UserQuery();
//        userQuery.setQueryType("0");
//        List<User> users = userService.userPage(userQuery);
//        System.out.println(users);
        List<auth_user> methods = whyUtil.methods();
        System.out.println(methods);
    }

    /**
     * 测试配置类
     * @param
     * @return
     */
    @RequestMapping("/peoper")
    @ResponseBody
    public void peoper(){
        System.out.println("测试配置类");
        WxProperties properties = new WxProperties();
        System.out.println(properties.getToken());
    }

    /**
     *
     * @param method
     * @param args
     */
    @GetMapping("/selectMethod")
    @ResponseBody
    public Result<Map<String,Object>> selectMethod(String method, Map<String,Object> args){
        Result<Map<String,Object>> result = new Result<>();

        try {
            Class<?> aClass = Class.forName("com.web.base.common.AutoSelectMethod");
//            aClass.getDeclaredMethod(method,args);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
