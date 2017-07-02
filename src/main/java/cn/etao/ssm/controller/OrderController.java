package cn.etao.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by lil.yang on 2017/5/23.
 */
@Controller
@RequestMapping("qtwalle")
public class OrderController {
    @RequestMapping(value = "/order/{orderNum}", method = {RequestMethod.GET,RequestMethod.POST},  produces = "text/json;charset=UTF-8")
    public String GetQtraceIdsByOrderId(@PathVariable("orderNum") String orderNum, Model model){

        model.addAttribute("list","abc");

        return "GetQtraceIds";
    }
}
