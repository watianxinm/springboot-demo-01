package com.study.demo01web.Mapper;

import com.study.demo01web.Pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

//该注解表示这是spring的一个mapper接口  Dao层
@Mapper
@Repository
public interface EmployeeMapper {
    //1.查询全部员工信息
    List<Employee> queryEmps();
    //2.根据id查询员工信息
    Employee queryEmpById(Integer id);
    //3.新增员工信息
    int addEmp(Employee emp);
    //4.修改员工信息
    int updateEmp(Employee emp);
    //5.根据id删除员工信息
    int deleteEmp(Integer id);
}
