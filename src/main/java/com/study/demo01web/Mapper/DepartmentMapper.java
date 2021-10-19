package com.study.demo01web.Mapper;

import com.study.demo01web.Pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DepartmentMapper {
    //1.查询全部部门信息
    List<Department> queryDepts();
    //2.根据id查询部门信息
    Department queryDeptById(Integer id);
    //3.新增部门信息
    int addDept(Department dept);
    //4.修改部门信息
    int updateDept(Department dept);
    //5.删除部门信息
    int deleteDept(Integer id);
}
