package com.wang.novelweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wang.novelweb.Mapper")
public class NovelwebApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelwebApplication.class, args);
    }

}
