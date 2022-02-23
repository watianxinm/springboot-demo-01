package com.study.demo01web.Mapper;

import com.study.demo01web.Pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface RoleMapper extends JpaRepository<Role,Integer> {

}
