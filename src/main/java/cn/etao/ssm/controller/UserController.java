package cn.etao.ssm.controller;
import cn.etao.ssm.pojo.User;
import cn.etao.ssm.redis.RedisService;
import cn.etao.ssm.service.IUserService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by yangli on 2016/8/10.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = Logger.getLogger(UserController.class);

    @Resource
    private IUserService userService;
    @Autowired
    protected RedisService redisService;

    @RequestMapping(value = "/showUser/{id}" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息", httpMethod = "Get", consumes = "application/json;charset=UTF-8", notes = "查询用户信息")
    public String toIndex(@ApiParam(value = "用户ID") @PathVariable("id") int id, Model model){
        int userId =id;// Integer.parseInt(request.getParameter("id"));
        User user = this.userService.getUserById(userId);
        model.addAttribute("user", user);

        // 记录debug级别的信息
        logger.debug("This is debug message.");

        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");
//        String redis_name=redisService.get("name",String.class);
//
//        redisService.set("name",redis_name);
        //String redis_name=redisService.get("redis_name",String.class);
        //redis_name:${redis_name}
        model.addAttribute("redis_name",model);


        return "showUser";
    }

    @RequestMapping(value = "/saveUser",method = RequestMethod.GET)
    @ApiOperation(value = "保存用户信息", httpMethod = "Get", consumes = "application/json;charset=UTF-8", notes = "保存用户信息")
    public String save(HttpServletRequest request,Model model){
        User user=new User();
        user.setAge(20);
        user.setPassword("123");
        user.setUserName(request.getParameter("name"));
        if(this.userService.saveUser(user)>0)
        {
            model.addAttribute("user",user);
        }

        //redisService.set("redis_name",user.getUserName());
        return "saveUser";
    }
}