package cn.etao.ssm.service.impl;

import cn.etao.ssm.dao.IEmployeeDao;
import cn.etao.ssm.pojo.Employee;
import cn.etao.ssm.service.IEmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yangli on 2016/8/23.
 */
@Service("employeeService")
public class EmployeeServiceImpl implements IEmployeeService {
    @Resource
    private IEmployeeDao employeeDao;

    @Override
    public Employee getEmployeeById(int employeeId) {
        return employeeDao.selectByPrimaryKey(employeeId);
    }

    @Override
    public int saveEmployee(Employee employee) {
        return employeeDao.insertSelective(employee);
    }

    @Override
    public List<Employee> getEmployeeList() {
        return employeeDao.selectList();
    }
}
