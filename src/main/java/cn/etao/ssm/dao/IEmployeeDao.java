package cn.etao.ssm.dao;

import cn.etao.ssm.pojo.Employee;

import java.util.List;

/**
 * Created by yangli on 2016/8/23`.
 */
public interface IEmployeeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Employee record);

    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);

    List<Employee> selectList();
}
