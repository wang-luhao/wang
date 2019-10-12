package com.wang.service.Controller;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;

@Controller
public class LookController {

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @ResponseBody
    @RequestMapping("/show")
    public Long Show(){
        DruidDataSource druidDataSource = (DruidDataSource)dataSource;
        return druidDataSource.getTimeBetweenEvictionRunsMillis();
    }
}
