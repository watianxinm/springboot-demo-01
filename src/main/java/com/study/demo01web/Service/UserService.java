package com.study.demo01web.Service;

import com.study.demo01web.Mapper.UserMapper;
import com.study.demo01web.Pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //创建新用户
    public User create(User user){
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return userMapper.save(user);
    }
    //通过用户名查找用户信息
    public User findByUsername(String username){
        return userMapper.findByUsername(username);
    }
}
