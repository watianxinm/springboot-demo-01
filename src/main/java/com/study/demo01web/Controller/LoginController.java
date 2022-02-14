package com.study.demo01web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String doLogin(@RequestParam("username")String username, @RequestParam("password") String password, Model model, HttpSession session){
        //设置登录成功的条件
        if(!StringUtils.isEmpty(username) && ("123456").equals(password)){
            session.setAttribute("userlogin",username);
            return "redirect:/emps";
        }else{
            model.addAttribute("msg", "用户名或密码错误");
//            return "/login";
            return "login";
        }
    }

    //注销登录
    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }
}
