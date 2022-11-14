package com.study.demo01web.Util;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从请求头中的Authorization字段中获取JWTToken
        String JWTToken = request.getHeader("Authorization");
        try{
        //校验JWTToken
        DecodedJWT decodedJWT = JWTUtils.decodeToken(JWTToken);
        //通过解析 解析出payload中的随机token，从Redis中获取用户信息value
        //...
        return true;
        }catch (SignatureVerificationException e){
            System.out.println("无效签名");
            e.printStackTrace();
        }catch (TokenExpiredException e){
            System.out.println("token已经过期");
            e.printStackTrace();
        }catch (AlgorithmMismatchException e){
            System.out.println("算法不一致");
            e.printStackTrace();
        }catch (Exception e){
            System.out.println("token无效");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
