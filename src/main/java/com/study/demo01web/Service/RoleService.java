package com.study.demo01web.Service;

import com.study.demo01web.Mapper.RoleMapper;
import com.study.demo01web.Pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleMapper roleMapper;

    //创建角色
    public Role create(Role role){
        return roleMapper.save(role);
    }
    //通过id获取角色信息
    public Role findById(Integer id){
        return roleMapper.findById(id).orElse(null);
    }
}
