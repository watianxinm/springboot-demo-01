package com.study.demo01web.Pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

//员工表
@Data
//@NoArgsConstructor
public class Employee {

    private Integer id;
    private String eName;
    private String email;
    private Integer gender; //0:女  1:男
    private Department department;
    private Date birth;

    public Employee(Integer id, String eName, String email, Integer gender, Department department) {
        this.id = id;
        this.eName = eName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.birth = new Date();
    }

}
