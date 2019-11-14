package com.wang.novelweb.Controller;


import com.wang.novelweb.Entity.UserEntity;
import com.wang.novelweb.Service.SaveUserBookService;
import com.wang.novelweb.Service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private SaveUserBookService saveUserBookService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setSaveUserBookService(SaveUserBookService saveUserBookService) {
        this.saveUserBookService = saveUserBookService;
    }

    /**
     * 信息
     */
    @RequestMapping("userInfo")
    public String userInfo(Map<String,Object> map){
        UserEntity userEntity = (UserEntity)SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        map.put("user",userEntity);
        map.put("saveBooks", saveUserBookService.selectSaveBooks(userEntity.getId()));
        return "userInfo";
    }

    /*
     * 保存
     */


    /*
     * 修改
     */


    /*
     * 删除
     */


}
