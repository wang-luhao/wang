package com.wang.eurekaclient.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * user
 *
 * @author wlh
 */
@Data
public class User implements Serializable {
    private String userid;

    private String username;

    private String userpassword;

    private static final long serialVersionUID = 1L;

    public User(String userid, String username, String userpassword) {
        this.userid = userid;
        this.username = username;
        this.userpassword = userpassword;
    }
}