package com.study.demo01web;

import org.junit.Test;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;
import org.springframework.security.core.SpringSecurityCoreVersion;

public class TestSpringVersion {
    @Test
    public void getSpringVersion(){
        String springVersion = SpringVersion.getVersion();
        String sbVersion = SpringBootVersion.getVersion();
        String ssVersion = SpringSecurityCoreVersion.getVersion();
        System.out.println(springVersion);
        System.out.println(sbVersion);
        System.out.println(ssVersion);
    }
}
