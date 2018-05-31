package com.bsg.api.interceptor;

import com.bsg.api.util.EncryptionUtils;
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zhang on 2017/5/21.
 */
public class ShiroRealmInterceptor{ // extends AuthorizingRealm
    private static final String USER_NAME = "luoguohui";
    private static final String PASSWORD = "123456";
    /*
    * 授权
    */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Set<String> roleNames = new HashSet<String>();
//        Set<String> permissions = new HashSet<String>();
//        roleNames.add("administrator");//添加角色
//        permissions.add("newPage.jhtml");  //添加权限
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
//        info.setStringPermissions(permissions);
//        return null;
//    }
//    /*
//    * 登录验证
//    */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
//        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
//        if(token.getUsername().equals(USER_NAME)){
//            return new SimpleAuthenticationInfo(USER_NAME, EncryptionUtils.getMD5(PASSWORD), getName());
//        }else{
//            throw new AuthenticationException();
//        }
//
//    }
}
