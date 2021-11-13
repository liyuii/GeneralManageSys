package com.web.LoginModule.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.base.common.SystemConstant;
import com.web.base.config.WebSocketConfig;
import com.web.LoginModule.entity.Loginer;
import com.web.LoginModule.entity.tx_msg;
import com.web.LoginModule.service.ChatService;
import com.web.LoginModule.serviceImpl.ChatServiceImpl;
import com.web.util.MessageUtils;
import com.web.util.contextUtil;
import com.web.util.random.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
@ServerEndpoint(value = "/test/one",configurator = WebSocketConfig.class)
@Component
public class WebsocketController {

    /** 记录当前在线连接数 */
    private static AtomicInteger onlineCount = new AtomicInteger(0);

    private static Map<String,Session> map = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {

        onlineCount.incrementAndGet(); // 在线数加1
        log.info("有新连接加入：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
        //获取当前用户信息，用来生成在线用户列表
        Loginer loginer = (Loginer)session.getUserProperties().get(SystemConstant.SESSION_SYSUSERINFO);
        map.put(loginer.getLoginName(),session);
        //根据消息类型，生成返回格式
        String message = MessageUtils.getMessage("system",null,getNames());
        broadcastAllUsers(message);
    }

    private void broadcastAllUsers(String message) {
        Set<String> names = map.keySet();
        for(String name:names){
            Session session = map.get(name);
            session.getAsyncRemote().sendText(message);
        }
    }

    private Set<String> getNames() {
        return map.keySet();
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        onlineCount.decrementAndGet(); // 在线数减1
        Loginer loginer = (Loginer)session.getUserProperties().get(SystemConstant.SESSION_SYSUSERINFO);
        map.remove(loginer.getLoginName());
        String message = MessageUtils.getMessage("system",null,getNames());
        broadcastAllUsers(message);
        log.info("有一连接关闭：{}，当前在线人数为：{}", session.getId(), onlineCount.get());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message
     *            客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws JsonProcessingException {
        log.info("服务端收到客户端[{}]的消息:{}", session.getId(), message);

        ObjectMapper objectMapper = new ObjectMapper();
        tx_msg msg = objectMapper.readValue(message, tx_msg.class);

        String mess = objectMapper.writeValueAsString(msg);

        if("single".equals(msg.getMsgType())){

            //找到要发送用户的session
            Iterator<Map.Entry<String, Session>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, Session> next = iterator.next();
                if(msg.getToName().equals(next.getKey())){
                    Session toSession = next.getValue();

                    //add
                    ChatService chatService = contextUtil.getBean(ChatServiceImpl.class);
                    tx_msg savaMsg  = new tx_msg();
                    savaMsg.setMsgid(UUIDUtil.generateUuid());
                    Loginer fromUser = chatService.getByName(msg.getFromName());
                    savaMsg.setFromId(fromUser.getLoginId());
                    savaMsg.setFromName(msg.getFromName());
                    Loginer toUser = chatService.getByName(msg.getToName());
                    savaMsg.setToId(toUser.getLoginId());
                    savaMsg.setToName(msg.getToName());
                    savaMsg.setMsgContent(msg.getMsgContent());
                    savaMsg.setMsgType(msg.getMsgType());
                    savaMsg.setAddTime(new Date());
                    chatService.addMsg(savaMsg);

                    System.out.println("插入消息");

                    //一对一发送
                    this.sendMessage(mess, toSession);
                }
            }
        }else if("group".equals(msg.getMsgType())){

            //广播
//            Set<Map.Entry<String, Session>> entries = map.entrySet();
//            for (Map.Entry entry:entries){
//                Session toSession = (Session)entry.getValue();
//                if(!session.getId().equals(toSession.getId())){
//                    this.sendMessage(mess, toSession);
//                }
//            }

        }else{
            System.out.println("未完待续...");
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String message, Session toSession) {
        try {
            log.info("服务端给客户端[{}]发送消息{}", toSession.getId(), message);
            toSession.getBasicRemote().sendText(message);
        } catch (Exception e) {
            log.error("服务端发送消息给客户端失败：{}", e);
        }
    }

    public boolean judge(String name){

        Iterator<String> iterator = map.keySet().iterator();

        while (iterator.hasNext()){
            String next = iterator.next();
            if(next.equals(name))
                return false;
        }
        return true;
    }

}
