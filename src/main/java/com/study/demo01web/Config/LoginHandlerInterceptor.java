package com.study.demo01web.Config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    //执行Controller之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    //执行Controller之后，DispatcherServlet渲染视图界面之前执行，即可对ModelAndView执行操作
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //用户登录成功之后，可通过Session获取用户登录信息
        Object userlogin = request.getSession().getAttribute("userlogin");
        //当用户没有登录，则拦截所有请求，最终返回到登录页面
        if (userlogin == null) {
            request.setAttribute("msg", "没有权限，请先登录");
            //将请求重定向到登录页面
            request.getRequestDispatcher("/login.html").forward(request, response);
        }
    }

    //在DispatcherServlet渲染视图后执行。常被用于清理资源
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}
