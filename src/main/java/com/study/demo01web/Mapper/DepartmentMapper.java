package com.study.demo01web.Mapper;

import com.study.demo01web.Pojo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    //1.查询全部部门信息
    List<Department> queryDepts();
    //2.根据id查询部门信息
    Department queryDeptById(Integer id);
    //3.新增部门信息
    String addDept(Department dept);
    //4.修改部门信息
    String updateDept(Department dept);
    //5.删除部门信息
    String deleteDept(Integer id);
}
