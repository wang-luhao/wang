package com.wang.eurekaclientnovel.controller;

import com.wang.eurekaclientnovel.entity.User;
import com.wang.eurekaclientnovel.feign.userFeignClient;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * author  wlh
 * date  2020/1/11 0011 9:05
 */

@RestController
@Log
public class userController {

//    private RestTemplate restTemplate;
    private LoadBalancerClient loadBalancerClient;
    private userFeignClient userFeignClient;
//    @Autowired
//    public void setRestTemplate(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
    @Autowired
    public void setLoadBalancerClient(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    @Autowired
    public void setUserFeignClient(com.wang.eurekaclientnovel.feign.userFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @ResponseBody
    @GetMapping("/user/{id}")
    public User findUser(@PathVariable Long id) {
//        return this.restTemplate.getForObject("http://user-client/user/"+id,User.class);
        return userFeignClient.findUser(id);
    }

    @GetMapping("user/info")
    public void LogInfo(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("user-client");
        log.info(serviceInstance.getInstanceId()+":"+serviceInstance.getHost()+":"+
                serviceInstance.getPort());
    }
}
