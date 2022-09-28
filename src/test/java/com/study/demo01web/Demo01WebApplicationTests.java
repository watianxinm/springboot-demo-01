package com.study.demo01web;

import com.alibaba.druid.pool.DruidDataSource;
import com.study.demo01web.Pojo.User;
import com.study.demo01web.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
public class Demo01WebApplicationTests {

    @Autowired
    DataSource dataSource;
    @Test
    public void testDruid() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection =   dataSource.getConnection();
        System.out.println(connection);
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
        //关闭连接
        connection.close();
    }

    @Autowired
    UserService userService;

    @Test
    public void addAdmin(){
        userService.create(new User(2,"admin","123456",1));
        User user = userService.findByUsername("admin");
        System.out.println(user);
    }

}
