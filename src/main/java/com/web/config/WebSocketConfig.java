package com.web.config;

import com.web.common.SystemConstant;
import com.web.entity.Loginer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.servlet.ShiroHttpSession;
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
            Loginer loginer= (Loginer)shiroSession.getAttribute(SystemConstant.SESSION_SYSUSERINFO);

            if(loginer!=null ) {
                sec.getUserProperties().put(SystemConstant.SESSION_SYSUSERINFO, loginer);
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