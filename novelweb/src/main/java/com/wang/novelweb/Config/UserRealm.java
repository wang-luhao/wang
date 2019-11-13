package com.wang.novelweb.Config;


import com.wang.novelweb.Entity.UserEntity;
import com.wang.novelweb.Service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import sun.rmi.runtime.Log;


import javax.annotation.Resource;

@Log4j
public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService UserService;


    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if(token.getPrincipal()==null){
            return null;
        }
        String username = (String) token.getPrincipal();
        UserEntity userEntity = UserService.findUser(username);
        if (userEntity == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(userEntity, userEntity.getPassword(), getName());


    }


    //执行授权

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        // TODO Auto-generated method stub
        log.info("授权");
        //获取当前登录用户
        UserEntity userEntity= (UserEntity) arg0.getPrimaryPrincipal();
//        String username = (String)arg0.getPrimaryPrincipal();
//        UserEntity userEntity = UserService.findUser(username);
        //给资源授权
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission(userEntity.getPermission());
        return simpleAuthorizationInfo;
    }



}

