package cn.etao.ssm.controller;

import cn.etao.ssm.pojo.Employee;
import cn.etao.ssm.service.IEmployeeService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by yangli on 2016/8/23.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private IEmployeeService employeeService;

    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    @ApiOperation(value = "获取员工列表", httpMethod = "Get", consumes = "application/json;charset=UTF-8", notes = "获取员工列表")
    public ModelAndView list(){
        ModelAndView model = new ModelAndView("employeeView");
        List<Employee> list=employeeService.getEmployeeList();
        model.addObject("list",list);
        model.addObject("viewPath", "employee/list");
        return  model;
    }

    @RequestMapping(value = "/save",method = RequestMethod.GET)
    public String save(String name,Model model){
        Employee employee=new Employee();
        employee.setName("name");
        employee.setPassword("123");
        employee.setAge(18);
        employee.setBirthday(new Date());
        employee.setCreatetime(new Date());
        employee.setIsenable(true);
        employee.setSex(true);
        //employee.setId(new Integer(1));
        Integer num= employeeService.saveEmployee(employee);

        model.addAttribute("num",employee.getId());
        return "ok";
    }
}
