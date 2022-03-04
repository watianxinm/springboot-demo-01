package com.study.demo01web.Config;

import com.study.demo01web.Service.MyUserDetailsService;
import com.study.demo01web.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //登录页面允许所有人访问
        //请求授权的规则
        http
                .authorizeRequests() //拦截请求  创建FilterSecurityInterceptor
                .antMatchers("/","/login.html").permitAll() //访问登录页面不需要权限验证
                .antMatchers("/emps").hasRole("ADMIN")
                .and()
                .formLogin() //没有权限会到默认的登录界面  内部注册UsernamePasswordAuthenticationFilter
                .loginPage("/login.html") //指定表单登录页面地址
                .loginProcessingUrl("/user/login") //form登录表单post请求URL提交地址，默认为/login
//                .successForwardUrl("/emps") //进行路径跳转的方式为转发(forward)
                .defaultSuccessUrl("/emps") //进行路径跳转的方式为重定向(redirect)
                .permitAll();
        http.csrf().disable(); //springsecurity在开启csrf防护的情况下，/logout必须是以POST方法提交，而<a>请求是GET方法，会报404
        http
                .logout() //用户退出登录
                .logoutUrl("/user/logout") //logoutFilter要读取的URL，也就是指定springsecurity拦截的注销url
                .logoutSuccessUrl("/login.html"); //用户退出后要被重定向的url
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
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
