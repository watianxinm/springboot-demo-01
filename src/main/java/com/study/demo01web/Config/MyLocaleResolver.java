package com.study.demo01web.Config;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class MyLocaleResolver implements LocaleResolver {
    //解析请求
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //获取链接请求中的语言参数
        String language = request.getParameter("lang");
        //如果没有参数就使用默认配置
        Locale defaultLocale = Locale.getDefault();
        //如果请求中携带参数 则用自定义的语言
        if(!StringUtils.isEmpty(language)){
            //zh_CN
            String[] s = language.split("_");
            //国家_地区
            defaultLocale = new Locale(s[0],s[1]);
        }
        return defaultLocale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
