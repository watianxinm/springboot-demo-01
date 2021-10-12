package com.study.demo01web.Dao;

import com.study.demo01web.Pojo.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//部门Dao
@Repository
public class DepartmentDao {

    //1.模拟数据库中的数据
    private static Map<Integer, Department> departments = null;
    static {
        //创建一个部门表
        departments = new HashMap<Integer, Department>();
        departments.put(101,new Department(101,"教学部"));
        departments.put(102,new Department(102,"市场部"));
        departments.put(103,new Department(103,"教研部"));
        departments.put(104,new Department(104,"运营部"));
        departments.put(105,new Department(105,"后勤部"));
    }

    //2.模拟数据库：查
    // 获得所有部门信息
    public Collection<Department> getDepartments(){
        return departments.values();
    }
    //3.通过id获得部门(单个)
    public Department getDepartmentsById(Integer id){
        return departments.get(id);
    }
}
