package com.wang.novelweb.Config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShiroConfig {

    /**
     * 403页面
     */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver resolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        /*未授权处理页*/
        properties.setProperty("UnauthorizedException", "403.html");
        resolver.setExceptionMappings(properties);
        return resolver;
    }
    /**
     * 密码验证器
     */
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher(){
        return new MyMatcher();
    }

    /**
     * 缓存验证器
     */
    @Bean
    public RedisCacheManager cacheManager(){
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        redisCacheManager.setPrincipalIdFieldName("username");
        redisCacheManager.setExpire(200000);
        return redisCacheManager;
    }

    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1:6379");
        // 配置过期时间
        return redisManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * sessionManager
     */

    @Bean
    public SessionManager SessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setCacheManager(cacheManager());
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }


    /**
     *记住我Cookie对象参数
     */
    private SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name=rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //cookie生效时间为10秒
        simpleCookie.setMaxAge(10);
        return simpleCookie;
    }

    /**
     *Cookie管理对象
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        return  cookieRememberMeManager;
    }
    /*
     * 自定义记住我过滤器
     */

//    @Bean
//    public MyRememberFilter myRememberFilter(){
//        return new MyRememberFilter();
//    }

    /**
     * 配置核心安全管理器
     */
    @Bean(name = "shiroRealm")
    public UserRealm shiroRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCachingEnabled(true);
        userRealm.setAuthenticationCachingEnabled(true);
        userRealm.setAuthenticationCacheName("authenticationCache");
        userRealm.setAuthorizationCachingEnabled(true);
        userRealm.setAuthorizationCacheName("authorizationCache");
        return userRealm;
    }

    @Bean(name = "securityManager")
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入自定义myRealm
        securityManager.setRealm(shiroRealm());
        //注入自定义cacheManager
        securityManager.setCacheManager(cacheManager());
        //注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        //注入自定义sessionManager
        securityManager.setSessionManager(SessionManager());
        return securityManager;
    }

    /**
     * shiro的生命周期
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 过滤器
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        Map<String, Filter> filterMap=new LinkedHashMap<>();
//        filterMap.put("MyRememberFilter",myRememberFilter());

        //拦截
        Map<String, String> filterChainMap = new LinkedHashMap<>();
        filterChainMap.put("/logout", "logout");
        filterChainMap.put("/lib/**", "anon");
        filterChainMap.put("/websocket","anon");
        filterChainMap.put("/ajaxLogin", "anon");
        filterChainMap.put("/login.action", "anon");
        filterChainMap.put("/random", "anon");
        filterChainMap.put("/book/**", "anon");
        filterChainMap.put("/403", "anon");
        filterChainMap.put("/index", "anon");
        //filterChainMap.put("/saveUserBook/**", "anon");
        filterChainMap.put("/**", "authc");

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/success");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap);
        return shiroFilterFactoryBean;
    }


    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    @ConditionalOnMissingBean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }

    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}

