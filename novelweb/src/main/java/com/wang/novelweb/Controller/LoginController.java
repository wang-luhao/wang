package com.wang.novelweb.Controller;


import com.wang.novelweb.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
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

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @RequestMapping("success")
    public String toSuccess(String username, Map<String, Object> map) {
        map.put("username", username);
        return "index";
    }


    @RequestMapping(value = "/random", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> RandomNum() {
        Map<String, Object> resultMap = new HashMap<>();
        Random rl = new Random();
        List<Integer> list = new ArrayList<Integer>();
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
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        System.out.println(username);
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password, "login");
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");
        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
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
            System.out.println("密码正确");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("IncorrectCredentialsException密码不正确");
            map.put("msg", "密码不正确");
            return "login";
        } catch (UnknownAccountException uae) {
            System.out.println("UnknownAccountException账号不存在");
            map.put("msg", "账号不存在");
            return "login";
        } catch (AuthenticationException ae) {
            System.out.println("AuthenticationException状态不正常");
            map.put("msg", "状态不正常");
            return "login";
        }
        map.put("username",username);
        Session session = currentUser.getSession();
        session.setAttribute("user", userService.findUser(username));
        return "index";
    }

    @RequestMapping("logout")
    public String logout() {
        return "login";
    }
}
