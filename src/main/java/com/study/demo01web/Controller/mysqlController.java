package com.study.demo01web.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class mysqlController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/getValues")
    public List<Map<String, Object>> getValues(){
        String sql = "select * from department";
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql);
        return mapList;
    }

    @RequestMapping("addValues")
    public String addValues(){
        String sql = "insert into department values(106, '设计部')";
        jdbcTemplate.update(sql);
        return "add-ok";
    }

    @RequestMapping("/updateValues/{id}")
    public String updateValues(@PathVariable("id") int id){
        String sql = "update department set departmentName = '开发部' where id=?";
        jdbcTemplate.update(sql,id);
        return "update-ok";
    }

    @RequestMapping("/updateValue/{id}")
    public String updateValue(@PathVariable("id") int id){
        String sql = "update department set departmentName = ? where id=" + id;
        Object obj = new Object();
        obj = "开发部再试";
        jdbcTemplate.update(sql,obj);
        return "update-okk";
    }

    @RequestMapping("/deleteValues/{id}")
    public String deleteValues(@PathVariable("id") int id){
        String sql = "delete from department where id =" +id;
        jdbcTemplate.update(sql);
        return "delete-ok";
    }

}
