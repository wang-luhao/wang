package com.wang.eurekaclient.controller;

import com.wang.eurekaclient.entity.User;
import com.wang.eurekaclient.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * author  wlh
 * date  2020/1/11 0011 9:05
 */

@RestController
@Log
public class userController {


    private UserService userService;

    @Autowired
    public void setUserDao(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public User findUser(@PathVariable Long id) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> collection = user.getAuthorities();
            for(GrantedAuthority authority:collection){
                System.out.println("当前的用户是" + user.getUsername() + ",角色是" +
                        authority.getAuthority());
            }
        }
        return userService.select(id);
    }
}
