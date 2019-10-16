package com.wang.service.Controller;

import com.wang.service.Service.NovelUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/User")
public class UserController {


    private NovelUserService novelUserService;

    @Autowired
    public void setNovelUserService(NovelUserService novelUserService) {
        this.novelUserService = novelUserService;
    }

    @ResponseBody
    @RequestMapping("/show")
    public List Show(){

        return novelUserService.getUsers();
    }
}
