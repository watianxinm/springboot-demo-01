package com.study.demo01web.Service;

import com.study.demo01web.Mapper.DepartmentMapper;
import com.study.demo01web.Pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;
    //1.查询全部部门信息
    public List<Department> queryDepts(){
        return departmentMapper.queryDepts();
    }
    //2.根据id查询部门信息
    public Department queryDeptById(Integer id){
        return departmentMapper.queryDeptById(id);
    }
    //3.新增部门信息
    public String addDept(Department dept){
        departmentMapper.addDept(dept);
        return "OK";
    }
    //4.修改部门信息
    public String updateDept(Department dept){
        departmentMapper.updateDept(dept);
        return "OK";
    }
    //5.删除部门信息
    public String deleteDept(Integer id){
        departmentMapper.deleteDept(id);
        return "Ok";
    }
}
