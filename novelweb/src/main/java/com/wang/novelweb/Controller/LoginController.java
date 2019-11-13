package com.wang.novelweb.Controller;



import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


@Api(tags = "这个类的标签")
@Log4j
@Controller
public class LoginController {



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String toSuccess() {
        return "index";
    }


    @RequestMapping(value = "/random", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> RandomNum() {
        Map<String, Object> resultMap = new HashMap<>();
        Random rl = new Random();
        List<Integer> list = new ArrayList<>();
        while (list.size() != 6) {
            int num = rl.nextInt(33) + 1;
            if (!list.contains(num)) {
                list.add(num);
            }
        }
        resultMap.put("红色", list);
        Random bl = new Random();
        resultMap.put("蓝色", bl.nextInt(16) + 1);
        return resultMap;
    }


    @RequestMapping(value = "/ajaxLogin", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLogin(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        Map<String, Object> resultMap = new LinkedHashMap<>();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        try {
            SecurityUtils.getSubject().login(token);
        }catch (IncorrectCredentialsException ice) {
            resultMap.put("status", 500);
            resultMap.put("message", "密码不正确");
            log.info(username+":"+"密码不正确");
            return resultMap;
        }catch (UnknownAccountException uae) {
            resultMap.put("status", 500);
            log.info(username+":"+"账号不存在");
            resultMap.put("message", "账号不存在");
            return resultMap;
        } catch (AuthenticationException ae) {
            resultMap.put("status", 500);
            log.info(username+":"+"状态不正常");
            resultMap.put("message", "状态不正常");
            return resultMap;
        }
        resultMap.put("status", 200);
        resultMap.put("message", "登录成功");
        session.setAttribute("username", username);
        log.info(username+"登录成功");
        return resultMap;
    }


    @ApiOperation(value = "描述接口作用", notes = "对接口的额外说明", response = String.class)
    @RequestMapping(value = "/login.action", method = RequestMethod.POST)
    public String LoginAction(String username, String password, Map<String, Object> map) {
        if (username == null || password == null) {
            return "login";
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject currentUser = SecurityUtils.getSubject();
        try {
            currentUser.login(token);
        } catch (IncorrectCredentialsException ice) {
            log.info(username+":"+"密码不正确");
            map.put("msg", "密码不正确");
            return "login";
        } catch (UnknownAccountException uae) {
           log.info(username+":"+"账号不存在");
            map.put("msg", "账号不存在");
            return "login";
        } catch (AuthenticationException ae) {
            log.info(username+":"+"状态不正常");
            map.put("msg", "状态不正常");
            return "login";
        }
        log.info("密码正确");
        map.put("username",username);
        Session session = currentUser.getSession();
        session.setAttribute("user", username);
        return "index";
    }

    @RequestMapping("logout")
    public String logout() {
        return "login";
    }
}
