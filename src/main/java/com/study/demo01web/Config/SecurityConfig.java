package com.study.demo01web.Config;

import com.study.demo01web.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录页面允许所有人访问
        //请求授权的规则
        http
                .authorizeRequests()
                .antMatchers("/","/login.html").permitAll()
                .antMatchers("/emps").hasRole("ADMIN");

        //没有权限默认会到登录界面
//        http.formLogin();
    }

    //忽略静态资源的拦截
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("static/css/**","static/fonts/**","static/images/**","static/js/**");
    }

    //密码加密
    @Bean
    PasswordEncoder passwordEncoder(){
        //SpringSecurity 提供的一种编码器，我们也可以自己实现PasswordEncoder
        return new BCryptPasswordEncoder();
    }

    //重定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
        auth.userDetailsService(myUserDetailsService);
    }
}
