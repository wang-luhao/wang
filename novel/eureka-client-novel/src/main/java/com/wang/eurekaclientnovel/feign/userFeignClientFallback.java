package com.wang.eurekaclientnovel.feign;

import com.wang.eurekaclientnovel.entity.User;
import org.springframework.stereotype.Component;

/**
 * author  wlh
 * date  2020/1/17 0017 9:27
 */
@Component
public class userFeignClientFallback implements userFeignClient {
    @Override
    public User findUser(Long id) {
        User user = new User();
        user.setUserid(-1L);
        user.setUsername("默认用户");
        return user;
    }
}
