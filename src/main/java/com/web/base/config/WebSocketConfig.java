package com.web.base.config;

import com.web.ManageModule.entity.auth_user;
import com.web.base.common.SystemConstant;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

@Configuration
public class WebSocketConfig extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        /*如果没有监听器,那么这里获取到的HttpSession是null*/

        Session shiroSession = SecurityUtils.getSubject().getSession();
        if (shiroSession != null) {
            auth_user user= (auth_user)shiroSession.getAttribute(SystemConstant.SESSION_SYSUSERINFO);

            if(user!=null ) {
                sec.getUserProperties().put(SystemConstant.SESSION_SYSUSERINFO, user);
            }
        }
        super.modifyHandshake(sec, request, response);
    }

    /**
     * 注入一个ServerEndpointExporter,该Bean会自动注册使用@ServerEndpoint注解申明的websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}