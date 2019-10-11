package com.example.demo.Controller;





import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;





@RestController
public class HelloController {




    @Value("${spring.application.name}")
    private String serviceID;

    @ResponseBody
    @RequestMapping("/hello")
    public  Object index(){

        return "hello";
    }

}
