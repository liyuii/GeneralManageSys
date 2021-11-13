package com.web.base.shiro;

import com.web.LoginModule.entity.Function;
import com.web.LoginModule.entity.Loginer;
import com.web.LoginModule.entity.Role;
import com.web.LoginModule.service.LoginService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    /**
     * @MethodName doGetAuthorizationInfo
     * @Description 权限配置类
     * @Param [principalCollection]
     * @Return AuthorizationInfo
     * @Author
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称
        Loginer loginer = loginService.getLoginByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        List<Role> roles = loginService.rolePage(loginer.getLoginId());

        List<Function> functions = loginService.funPage(loginer.getLoginId());

        System.out.println(roles.toString());
        System.out.println(functions.toString());

        simpleAuthorizationInfo.addRoles(roles.stream().map(Role::getRoleName).collect(Collectors.toList()));
        simpleAuthorizationInfo.addStringPermissions(functions.stream().map(Function::getFunCode).collect(Collectors.toList()));
//        for (Role role : loginer.getRoles()) {
//            //添加角色
//            simpleAuthorizationInfo.addRole(role.getRoleName());
//            //添加权限
//            for (Function fun : role.getFunctions()) {
//                simpleAuthorizationInfo.addStringPermission(fun.getFunName());
//            }
//        }
        return simpleAuthorizationInfo;
    }

    /**
     * @MethodName doGetAuthenticationInfo
     * @Description 认证配置类
     * @Param [authenticationToken]
     * @Return AuthenticationInfo
     * @Author
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if (StringUtils.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        Loginer loginer = loginService.getLoginByName(name);
        if (loginer == null) {
            //这里返回后会报出对应异常
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(name, loginer.getLoginPassword().toString(), getName());
            return simpleAuthenticationInfo;
        }
    }
}


