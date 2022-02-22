package com.study.demo01web;

import com.study.demo01web.Pojo.Role;
import com.study.demo01web.Service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Slf4j
//@EntityScan(basePackageClasses = {com.study.demo01web.Pojo.Role.class})
public class RoleServiceTest extends Demo01WebApplicationTests{
    @Autowired
    private RoleService roleService;

    @Test
    public void create(){
        Role role1 = new Role();
        role1.setId(2);
        role1.setName("管理员");
        role1.setAuthority("ADMIN");
        roleService.create(role1);

        Role role2 = new Role();
//        role1.setId(3);
        role1.setName("普通用户");
        role1.setAuthority("USER");
        roleService.create(role2);
    }

    @Test
    public void findById(){
        Role result = roleService.findById(2);
        log.info("result{}",result);
    }
}
