package com.wang.eurekaclientnovel.feign;

import com.wang.eurekaclientnovel.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * author  wlh
 * date  2020/1/13 0013 16:20
 */
@FeignClient(name = "user-client")
public interface userFeignClient {

    @RequestMapping(value = "user/{id}",method = RequestMethod.GET)
    User findUser(@PathVariable("id") Long id);
}
