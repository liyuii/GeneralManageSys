package com.web.ManageModule.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.web.CommunicateModule.controller.WebsocketController;
import com.web.ManageModule.entity.*;
import com.web.ManageModule.service.*;
import com.web.base.common.Result;
import com.web.base.common.SystemConstant;
import com.web.base.common.WxProperties;
import com.web.util.SessionUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/myLogin")
public class LoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @Autowired
    PermissionService permService;

    @Autowired
    WxProperties properties;

    @GetMapping("/login")
    public void getRequest(){
        System.out.println("进入test3");
//        return "login";
    }

//    @RequestMapping("/testShiro")
//    @ResponseBody
//    public void testShiro(){
//        Subject subject = SecurityUtils.getSubject();
//        String loginer = (String)subject.getPrincipal();
//        if(StringUtils.isEmpty(loginer)){
//            System.out.println("已退出或者未登录");
//        }else {
//            System.out.println(loginer);
//        }
//    }

//    @RequestMapping("/in")
//    public String in(auth_user user){
//
//        Subject subject=SecurityUtils.getSubject();
//        //用户名密码令牌
//        AuthenticationToken token=new UsernamePasswordToken(user.getUsername(),user.getUserpassword());
//        //shiro 使用异常捕捉登录失败消息
//        try {
//            //将令牌传到shiro提供的login方法验证，需要自定义realm
//            subject.login(token);
//            //将用户信息保存到session
//            SessionUtil.getInstace().setSessionKey(SystemConstant.SESSION_SYSUSERINFO,user);
//            //借助WebsocketController中的map，判断当前用户是否在线，是则不允许重复登录
//            WebsocketController web = new WebsocketController();
//            if(web.judge(user.getUsername())){
//                return "index";
//            }else{
//                return "login";
//            }
//        } catch (Exception e){
//            e.printStackTrace();
//        }
////        System.out.println("验证通过");
//        return "login";
//    }

//    @RequestMapping("/logout")
//    public String loginout(){
//        System.out.println("退出登录");
////        Subject subject = SecurityUtils.getSubject();
////        subject.logout();
//        return "login";
//    }

    @GetMapping("/getUserAuths")
    public Result<auth_user> getUserAuths(){

        Result<auth_user> result = new Result<>();

        Subject subject = SecurityUtils.getSubject();
        String userName = (String)subject.getPrincipal();

        auth_user currentUser = userService.getUserByName(userName);
        List<auth_role> roles = roleService.rolePageByUser(currentUser.getUserid());
        for(auth_role role:roles){
            List<auth_menu> menus = menuService.getMenuByRole(role.getRoleid());
            //在这里处理一下菜单
            List<auth_menu> menuTree = menuService.manageMenuTree(menus);
            role.setMenus(menuTree);
        }

        for(auth_role role:roles){
            if(role.getMenus()!=null && role.getMenus().size()>=1){
                for(auth_menu menu:role.getMenus()){
                    List<auth_permission> perms = permService.getPermByMenu(menu.getMenuid());
                    menu.setPerms(perms);
                }
            }
        }
        currentUser.setRoles(roles);
        return result.setData("v","获取用户权限成功",currentUser);
    }

    @GetMapping("/getCode")
    public void getCode(HttpServletResponse response) {
        // 随机生成 4 位验证码
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 4);
        // 定义图片的显示大小
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(100, 30);
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");
        try {
            // 调用父类的 setGenerator() 方法，设置验证码的类型
            circleCaptcha.setGenerator(randomGenerator);
            // 输出到页面
            circleCaptcha.write(response.getOutputStream());
            // 打印
            System.out.println("验证码：" + circleCaptcha.getCode());
            // 关闭流
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试配置类
     * @param
     * @return
     */
    @GetMapping("/peoper")
    public void peoper(){
        System.out.println("测试配置类");
        System.out.println(properties.getToken());
    }
}
