package com.study.demo01web.Controller;

import com.alibaba.druid.util.StringUtils;
import com.study.demo01web.Pojo.User;
import com.study.demo01web.Service.MyUserDetailsService;
import com.study.demo01web.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public String doLogin(@RequestParam("username")String username, @RequestParam("password") String password, Model model, HttpSession session){
        if (StringUtils.isEmpty(username)) {
            model.addAttribute("msg", "用户名不能为空");
            return "login";
        }
        if (StringUtils.isEmpty(password)) {
            model.addAttribute("msg", "密码不能为空");
            return "login";
        }
        // 和数据库中的进行比对
        User user = userService.findByUsername(username);
        if (user != null) {
            if (passwordEncoder.matches(password,user.getPassword())) {
                // 用户名和密码都正确
                session.setAttribute("username",username);
//                session.getAttribute("username");
                return "redirect:/emps";
            } else {
                // 用户存在但是输入的密码不正确
                model.addAttribute("msg", "密码不正确，请重新输入");
                model.getAttribute("msg");
                // 返回到登录页面
                return "login";
            }
        }
        // 如果没有获取到相应的用户对象
        model.addAttribute("msg", "这个用户名不存在");
        return "login";
    }

    //注销登录
    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.html";
    }
}
