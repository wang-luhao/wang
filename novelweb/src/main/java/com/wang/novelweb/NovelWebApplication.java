package com.wang.novelweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableSwagger2
@SpringBootApplication
@MapperScan(basePackages = "com.wang.novelweb.Mapper")
public class NovelWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelWebApplication.class, args);
    }

}
