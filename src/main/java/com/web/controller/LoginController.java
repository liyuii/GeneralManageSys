package com.web.controller;

import com.web.common.SystemConstant;
import com.web.entity.Accountant;
import com.web.entity.Loginer;
import com.web.service.LoginService;
import com.web.util.SessionUtil;
import com.web.util.random.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/myLogin")
public class LoginController {

    @Autowired
    LoginService loginService;


    @RequestMapping("/login")
    public String getRequest(){
        System.out.println("进入test3");
        return "login";
    }

    @RequestMapping("/testShiro")
    @ResponseBody
    public void testShiro(){
        Subject subject = SecurityUtils.getSubject();
        String loginer = (String)subject.getPrincipal();
        if(StringUtils.isEmpty(loginer)){
            System.out.println("已退出或者未登录");
        }else {
            System.out.println(loginer);
        }
    }

    @RequestMapping("/in")
    public String in(Loginer loginer){

        Subject subject=SecurityUtils.getSubject();
        //用户名密码令牌
        AuthenticationToken token=new UsernamePasswordToken(loginer.getLoginName(),loginer.getLoginPassword());
        //shiro 使用异常捕捉登录失败消息
        try {
            //将令牌传到shiro提供的login方法验证，需要自定义realm
            subject.login(token);
            SessionUtil.getInstace().setSessionKey(SystemConstant.SESSION_SYSUSERINFO,loginer);
            WebsocketController web = new WebsocketController();
            if(web.judge(loginer.getLoginName())){
                return "chatPage";
            }else{
                return "login";
            }
            //没有异常表示验证成功
//            Loginer loginer1 = uservice.login(u);
//            session.setAttribute("user", loginer1);
        } catch (Exception e){
//            model.addAttribute("error","用户名或密码不正确！");
        }
//        System.out.println("验证通过");
        return "login";
    }

    @RequestMapping("/logout")
    public String loginout(){
        System.out.println("退出登录");
//        Subject subject = SecurityUtils.getSubject();
//        subject.logout();
        return "login";
    }

}
