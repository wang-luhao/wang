package com.wang.novelweb.Entity;


import lombok.Data;

import java.io.Serializable;


@Data
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户id
     */

    private int id;
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户密码
     */
    private String password;

    /**
     * 授权
     */
    private String  permission;

}
