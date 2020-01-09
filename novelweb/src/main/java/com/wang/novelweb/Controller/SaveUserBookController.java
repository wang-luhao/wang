package com.wang.novelweb.Controller;


import com.wang.novelweb.Entity.UserEntity;
import com.wang.novelweb.Service.SaveUserBookService;
import com.wang.novelweb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/saveUserBook")
public class SaveUserBookController {

    private SaveUserBookService saveUserBookService;
    private UserService userService;

    @Autowired
    public void setSaveUserBookService(SaveUserBookService saveUserBookService) {
        this.saveUserBookService = saveUserBookService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("saveUserBookLists")
    public Map saveUserBookLists(String username) {
        Map<String, Object> resultMap = new HashMap<>();
        Integer userId = userService.findUserId(username);
        resultMap.put("username", username);
        resultMap.put("saveUserBookLists", saveUserBookService.selectSaveBooks(userId));
        return resultMap;
    }

}
