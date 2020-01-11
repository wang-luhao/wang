package com.wang.eurekaclient.service;

import com.wang.eurekaclient.entity.User;

/**
 * author  wlh
 * date  2020/1/11 0011 10:13
 */
public interface UserService {
    User select(Long userId);
}
