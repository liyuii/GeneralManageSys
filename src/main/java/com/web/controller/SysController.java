package com.web.controller;

import com.web.util.string.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class SysController {

    @RequestMapping("/login")
    public String login(@RequestParam("command") String command, HttpServletRequest request){
        if(StringUtil.isNotEmpty(command)) {
            if ("linyuanpige".equals(command)) {
                HttpSession session = request.getSession();
                session.setAttribute("command",command);
                return "/index";
            } else {
                return "/login";
            }
        }
        return "/login";
    }
}
