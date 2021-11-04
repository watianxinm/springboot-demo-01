package com.study.demo01web.Controller;

import com.study.demo01web.Pojo.Department;
import com.study.demo01web.Pojo.Employee;
import com.study.demo01web.Service.DepartmentService;
import com.study.demo01web.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EmpsController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/emps")
    public String getList(Model model){
        //1.查询所有员工列表
        List<Employee> employees = employeeService.queryEmps();
        //3.将员工列表返回到前端页面显示
        model.addAttribute("emps" ,employees);
        return "dashboard";
    }

    //跳转到增加员工信息界面
    @GetMapping("/emp")
    public String addPages(Model model){
        //查出所有员工的部门信息
        List<Department> departments = departmentService.queryDepts();
        model.addAttribute("depts",departments);
        return "addEmps";
    }

    //增加员工信息并跳转到员工信息界面
    @PostMapping("/emp")
    public String addEmps(Employee employee){
        employeeService.addEmp(employee);
        return "redirect:/emps";
    }

    //跳转到修改员工信息界面
    @GetMapping("/emp{id}")
    public String tuUpdate(@PathVariable("id") Integer id, Model model){
        //根据员工id查询员工信息
        Employee employee = employeeService.queryEmpById(id);
        model.addAttribute("employ", employee);
        //查询全部的部门名称
        List<Department> departments = departmentService.queryDepts();
        model.addAttribute("departments", departments);
        return "updateEmps";
    }

    //修改员工信息并重定向到员工列表界面
    @PostMapping("/updateEmp")
    public String update(Employee employee){
        employeeService.updateEmp(employee);
        return "redirect:/emps";
    }

    //删除员工信息
    @RequestMapping("/deleteEmp{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeService.deleteEmp(id);
        return "redirect:/emps";
    }
}
