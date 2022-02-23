package com.study.demo01web;

import com.study.demo01web.Pojo.User;
import com.study.demo01web.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加用户信息
 */
@Slf4j
public class UserServiceTest extends Demo01WebApplicationTests{

    @Autowired
    UserService userService;

    @Test
    public void create(){
        User user1 = new User();
        user1.setUsername("admin");
        user1.setPassword("123456");
        user1.setRoleId(15);
        userService.create(user1);
        log.info("user1={}",user1);

        User user2 = new User();
        user2.setUsername("user");
        user2.setPassword("123456");
        user2.setRoleId(16);
        userService.create(user2);
        log.info("user2={}",user2);
    }

    @Test
    public void findByUsername(){
        User result = userService.findByUsername("admin");
        log.info("result:{}",result);
    }

}
