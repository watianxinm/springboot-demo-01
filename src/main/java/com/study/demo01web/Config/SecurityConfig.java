package com.study.demo01web.Config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录页面允许所有人访问
        http
                .authorizeRequests()
                .antMatchers("/","/login.html").permitAll()
                .antMatchers("/emps").hasRole("ZHANG");
//        http.formLogin();
    }

    //忽略静态资源的拦截
    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("static/css/**","static/fonts/**","static/images/**","static/js/**");
    }

    //重定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ZHANG");
    }
}
