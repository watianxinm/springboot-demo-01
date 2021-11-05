package com.study.demo01web.Pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//员工表
@Data
@NoArgsConstructor
public class Employee {

    private Integer id;
    private String eName;
    private String email;
    private Integer gender; //0:女  1:男
    private Department dept;
    private Integer deptId;
    private String departmentName;
    private Date birth;

    public Employee(Integer id, String eName, String email, Integer gender, Department dept, Integer deptId, String departmentName) {
        this.id = id;
        this.eName = eName;
        this.email = email;
        this.gender = gender;
        this.dept = dept;
        this.deptId = deptId;
        this.departmentName = departmentName;
        this.birth = new Date();
    }

}
