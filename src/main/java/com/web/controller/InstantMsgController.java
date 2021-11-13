package com.web.controller;

import com.web.common.SystemConstant;
import com.web.entity.Loginer;
import com.web.entity.Result;
import com.web.entity.tx_msg;
import com.web.entity.tx_people;
import com.web.service.ChatService;
import com.web.util.random.UUIDUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/msg")
public class InstantMsgController {

    @Autowired
    ChatService chatService;

    @GetMapping("/getPeople")
    public String getPeople(){

//        String name = (String)SecurityUtils.getSubject().getPrincipal();

        Session session = SecurityUtils.getSubject().getSession();
        Loginer loginer = (Loginer)session.getAttribute(SystemConstant.SESSION_SYSUSERINFO);

        return loginer.getLoginName();
    }

//    @PostMapping("/addMsg")
//    public Result<Object> addMsg(tx_msg msg){
//
//        Result<Object> result = new Result<>();
//
//        try {
//            msg.setMsgid(UUIDUtil.generateUuid());
//            msg.setAddTime(new Date());
//            chatService.addMsg(msg);
//        }catch (Exception e){
//            e.printStackTrace();
//            result.setCode("error");
//            result.setMessage("添加失败");
//        }
//
//        result.setCode("v");
//        result.setMessage("添加成功");
//        return result;
//    }

    @GetMapping("/getList")
    public Result<Object> getList(tx_msg msg){

        Result<Object> result = new Result<>();
        List<tx_msg> tx_msgs = null;
        try {
            tx_msgs = chatService.selectRecordsById(msg);
        }catch (Exception e){
            e.printStackTrace();
            result.setCode("error");
            result.setMessage("获取消息记录失败");
        }

        result.setCode("v");
        result.setMessage("获取消息记录成功");
        result.setData(tx_msgs);
        return result;
    }
}
