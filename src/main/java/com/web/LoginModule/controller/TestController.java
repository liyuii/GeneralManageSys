package com.web.LoginModule.controller;

import com.web.base.common.Result;
import com.web.LoginModule.entity.User;
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
@RequestMapping("/shiro")
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
        List<User> methods = whyUtil.methods();
        System.out.println(methods);

    }

    /**
     * 测试接收前端发送的json和form两种方式的数据
//     * @param recieve
     * @return
     */

    @RequestMapping("/test2")
    @ResponseBody
    public String testUtil2(){
        System.out.println("进来了");
//        System.out.println(recieve);
        return "ok";
    }

    /**
     * 测试redis
     * @param
     * @return
     */

    @RequestMapping("/redis")
    @ResponseBody
    public void redisTest(){
        String token = UUIDUtil.generateUuid();
        User usr = new User();
        usr.setUserId(UUIDUtil.generateUuid());
        usr.setUserName("zhangsan");
        usr.setUserPhone("1111111111");
        redisTemplate.opsForValue().set(token,usr);
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