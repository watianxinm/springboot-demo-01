package com.study.demo01web.Service;

import com.study.demo01web.Pojo.Role;
import com.study.demo01web.Pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 从数据库中获取用户信息
 * 实现认证和授权 在UserDetailsService接口中的loadUserByUsername()方法中进行
 */
@Configuration
@Slf4j
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    //将用户名，密码和用户权限传递给springsecurity
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        Role role = roleService.findById(user.getRoleId());
        if(!StringUtils.isEmpty(role)){
            //用户所拥有的权限 注意：必须以ROLE_开头
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
