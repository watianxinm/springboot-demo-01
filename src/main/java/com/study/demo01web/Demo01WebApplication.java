package com.study.demo01web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.study.demo01web.Mapper")
public class Demo01WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo01WebApplication.class, args);
    }

}
