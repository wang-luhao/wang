package com.wang.novelweb.Config;

import com.wang.novelweb.Interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfiguration implements WebMvcConfigurer {


    private LoginInterceptor loginInterceptor;

    @Autowired
    public void setLoginInterceptor(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry){
//        //设置系统访问的默认首页
//        registry.addViewController("/").setViewName("forward:login");
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login","/index","/");
    }
}
