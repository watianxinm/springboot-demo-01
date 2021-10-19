package com.study.demo01web.Service;

import com.study.demo01web.Mapper.EmployeeMapper;
import com.study.demo01web.Pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;
    //1.查询全部员工信息
    public List<Employee> queryEmps(){
        return employeeMapper.queryEmps();
    }
    //2.根据id查询员工信息
    public  Employee queryEmpById(Integer id){
        return employeeMapper.queryEmpById(id);
    }
    //3.新增员工信息
    public int addEmp(Employee emp){
        employeeMapper.addEmp(emp);
        return 1;
    }
    //4.修改员工信息
    public int updateEmp(Employee emp){
        employeeMapper.updateEmp(emp);
        return 1;
    }
    //5.根据id删除员工信息
    public int deleteEmp(Integer id){
        employeeMapper.deleteEmp(id);
        return 1;
    }
}
