package com.wang.eurekaclientnovel.controller;

import com.wang.eurekaclientnovel.entity.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
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

    private RestTemplate restTemplate;
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Autowired
    public void setLoadBalancerClient(LoadBalancerClient loadBalancerClient) {
        this.loadBalancerClient = loadBalancerClient;
    }

    @ResponseBody
    @GetMapping("/user/{id}")
    public User findUser(@PathVariable Long id) {
        log.info(this.loadBalancerClient.choose("user-client").getInstanceId());
        return this.restTemplate.getForObject("http://user-client/user/"+id,User.class);
    }
}
