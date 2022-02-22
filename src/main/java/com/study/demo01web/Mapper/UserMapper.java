package com.study.demo01web.Mapper;

import com.study.demo01web.Pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper extends JpaRepository<User, Integer> {
    //通过用户名获取用户信息
    User findByUsername(String username);
}
