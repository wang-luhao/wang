package com.wang.novelweb.Controller;






import com.wang.novelweb.Entity.UserEntity;
import com.wang.novelweb.Service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashMap;
import java.util.Map;


@Api(tags = "这个类的标签")
@Log4j
@Controller
public class LoginController {

    private UserService  userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("/logintest")
    public String LoginTest(String username,String password,Map<String,Object> map){
        UserEntity userEntity = userService.findUser(username);
        System.out.println("LoginAction" + userEntity.getName());
        return userEntity.getName();
    }

    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("success")
    public String toSuccess(){
        return "index";
    }

    @RequestMapping(value="/ajaxLogin",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> submitLogin(@RequestParam(value = "nickname") String username, @RequestParam(value = "pswd") String password) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        resultMap.put("登录","123");
        try {

            UsernamePasswordToken token = new UsernamePasswordToken(username,password,"login");
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
    @RequestMapping("/login.action")
    public String LoginAction(String username,String password, Map<String,Object> map){
        if(username == null||password == null){
            return "login";
        }
        System.out.println("LoginAction" + username);

        UsernamePasswordToken token = new UsernamePasswordToken(username,password,"login");
        Subject currentUser = SecurityUtils.getSubject();
        try{
            currentUser.login(token);
            if(currentUser.isAuthenticated()){
                map.put("username","username");
                return "index";
            }else{
                token.clear();
                return "login";
            }
        }catch (IncorrectCredentialsException ice){
            System.out.println("IncorrectCredentialsException密码不正确");
            map.put("msg","密码不正确");
        }catch (UnknownAccountException uae){
            System.out.println("UnknownAccountException账号不存在");
            map.put("msg","账号不存在");
        }catch (AuthenticationException ae){
            System.out.println("AuthenticationException状态不正常");
            map.put("msg","状态不正常");
        }
        return "login";
    }

    @RequestMapping("logout")
    public String logout(){
        return "login";
    }
}
