package com.wang.novelweb.Controller;




import com.wang.novelweb.Entity.Novel;
import com.wang.novelweb.Entity.NovelUser;
import com.wang.novelweb.Service.NovelUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Api(tags = "这个类的标签")
@Log4j
@Controller
public class LoginController {

    private NovelUserService novelUserService;

    @Autowired
    public void setNovelUserService(NovelUserService novelUserService) {
        this.novelUserService = novelUserService;
    }

    @RequestMapping(value = {"/","/index"})
    public  String index(){
        return "index";
    }


    @RequestMapping("/login")
    public String Login(){
        return "login.html";
    }


    @ApiOperation(value = "描述接口作用", notes = "对接口的额外说明", response = String.class)
    @RequestMapping("/login.action")
    public String LoginAction(@RequestParam String uphone,
                              @RequestParam(value = "upassword",defaultValue = "") String upassword,
                              HttpSession session){
        NovelUser novelUser = novelUserService.LoginCheck(uphone,upassword);
        if(null == novelUser){
            session.setAttribute("uphone1","1234");
            System.out.println(uphone);
            return "redirect:login";
        }
        session.setAttribute("user",novelUser.getUname());
        return "redirect:index";
    }
}
