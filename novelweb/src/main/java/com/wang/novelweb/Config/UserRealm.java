package com.wang.novelweb.Config;


import com.wang.novelweb.Entity.UserEntity;
import com.wang.novelweb.Service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;


import javax.annotation.Resource;


public class UserRealm extends AuthorizingRealm {

    @Resource
    private UserService UserService;



    //执行认证逻辑
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        if(username == null){
            return null;
        }
        UserEntity userEntity = UserService.findUser(username);
        if(userEntity == null){
            return null;
        }
        return new SimpleAuthenticationInfo(userEntity,userEntity.getPassword(),getName());


    }


    //执行授权

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
        // TODO Auto-generated method stub
//        System.out.println("授权");
//        //获取当前登录用户
//        Subject subject = SecurityUtils.getSubject();
//        UserEntity user = (UserEntity) subject.getPrincipal();
//        //给资源授权
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.addStringPermission(user.getName());
//        return simpleAuthorizationInfo;
        return null;
    }




}

