package com.study.demo01web.Dao;

import com.study.demo01web.Pojo.Department;
import com.study.demo01web.Pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//员工Dao
@Repository
public class EmployeeDao {

    //1.模拟数据库中的数据
    private static Map<Integer, Employee> employees = null;
    //创建一个员工表
    //员工有所属的部门 -- 自动注入 @Repository、@Autowired
    @Autowired
    private DepartmentDao departmentDao;
    static {
        employees = new HashMap<Integer, Employee>();
        employees.put(1001,new Employee(1001,"张三","123",0,new Department(101,"教学部")));
        employees.put(1002,new Employee(1002,"李四","124",1,new Department(102,"市场部")));
        employees.put(1003,new Employee(1003,"王五","125",0,new Department(103,"教研部")));
        employees.put(1004,new Employee(1004,"小米","126",1,new Department(104,"运营部")));
        employees.put(1005,new Employee(1005,"大米","127",0,new Department(105,"后勤部")));
    }

    //主键自增
    private static int initId = 1006;
    //新增一条员工记录
    public void add(Employee employee){
        //判断是否被分配id
        if(employee.getId() == null){
            employee.setId(initId ++);
        }
        employee.setDepartment(departmentDao.getDepartmentsById(employee.getDepartment().getId()));
        employees.put(employee.getId(),employee);
    }

    //查询全部员工信息
    public Collection<Employee> getEmployee(){
        Collection<Employee> values = employees.values();
        return values;
    }

    //根据id查询员工信息
    public Employee getEmployeeById(Integer id){
        Employee employee = employees.get(id);
        return employee;
    }

    //通过id删除员工
    public void deleteEmployeeById(Integer id){
        employees.remove(id);
    }
}
