package com.web.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class SessionUtil {
    private static SessionUtil sessionUtil;

    private SessionUtil() {
    }

    public static synchronized SessionUtil getInstace() {
        if (sessionUtil == null) {
            sessionUtil = new SessionUtil();
        }

        return sessionUtil;
    }

    public void setSessionKey(String key, Object value) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute(key, value);
    }

    public Object getSessionKet(String key) {
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return session.getAttribute(key);
    }

    public void test() {
    }
}
