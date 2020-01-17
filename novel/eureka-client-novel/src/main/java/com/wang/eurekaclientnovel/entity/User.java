package com.wang.eurekaclientnovel.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * user
 *
 * @author wlh
 */
@Data
public class User implements Serializable {
    private Long userid;

    private String username;

    private String userpassword;

    private static final long serialVersionUID = 1L;
}