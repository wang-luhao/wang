package com.wang.eurekaserver.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * author  wlh
 * date  2020/1/10 0010 15:51
 */
@EnableWebSecurity
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 在/eureka/**端点忽略csrf验证
//        http.csrf().disable();
        http.csrf().ignoringAntMatchers("/eureka/**");
        // 配置使请求需要通过httpBasic或form验证

        super.configure(http);
    }
}
