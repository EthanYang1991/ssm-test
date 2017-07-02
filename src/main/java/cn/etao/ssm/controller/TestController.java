package cn.etao.ssm.controller;

import cn.etao.ssm.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lil.yang on 2017/4/17.
 */
@Controller
@RequestMapping("/test")
public class TestController {


    @RequestMapping(value = "/person/abc/{id}" ,method = RequestMethod.GET)
    public String sayHello(@PathVariable("id") int id, Model model){



        return "test";
    }


    @RequestMapping(value = "/json" ,method = RequestMethod.GET)
    @ResponseBody
    public User getJson(){
        User user=new User();
        user.setUserName("zs");
        user.setAge(18);
        return user;
    }
}
