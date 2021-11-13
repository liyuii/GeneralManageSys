package com.web.interceptor;

import com.web.entity.User;
import com.web.util.string.StringUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进行拦截");
//        //获取请求的RUi:去除http:localhost:8080这部分剩下的
//        String uri = request.getRequestURI();
//        System.out.println(uri);
//        //UTL:除了login.jsp是可以公开访问的，其他的URL都进行拦截控制
//        if (uri.indexOf("/getLogin") >= 0 || uri.indexOf("testTest") >= 0) {//getLogin
//            System.out.println("这是登录页面");
//            return true;
//        }
//        //获取session
//        HttpSession session = request.getSession();
//        String cmd = (String) session.getAttribute("command");
//        //判断session中是否有用户数据，如果有，则返回true，继续向下执行
//        if (cmd != null) {
//            System.out.println("有数据");
//            return true;
//        }

//        String returnToken = request.getHeader("token");
//
//        if(StringUtil.isNotEmpty(returnToken)){
//
//            String token = returnToken.substring(1, returnToken.length() - 1);
//
//            HttpSession session = request.getSession();
//            String tokenSave = (String)session.getAttribute("token");
//            if(token.equals(tokenSave)){
//                System.out.println("token对比一致");
//                return true;
//            }
//
//        }else{
//            //不符合条件的给出提示信息，并转发到登录页面
//            response.sendRedirect("/login.html");
//            System.out.println("非法");
//        }
        
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
