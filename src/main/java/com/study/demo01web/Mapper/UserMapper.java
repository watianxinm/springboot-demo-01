package com.study.demo01web.Mapper;

import com.study.demo01web.Pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@Component(value = "JpaRepository-2")
//public interface UserMapper extends JpaRepository<User, Integer> {
//    //通过用户名获取用户信息
//    User findByUsername(String username);
//}

public interface UserMapper{
    //通过用户名获取用户信息
    User findByUsername(String username);

    User save(User user);
}