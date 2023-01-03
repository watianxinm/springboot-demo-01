package com.study.demo01web;

import com.alibaba.druid.pool.DruidDataSource;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;

@SpringBootTest
public class Demo01WebApplicationTests {

    //测试数据源
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

    /**
     * 测试jwt-root
     */
    //1. 生成JWT的token
    @Test
    public void testGeneralToken(){
        //指定Token过期时间为10秒
       Calendar calendar =  Calendar.getInstance();
       calendar.add(Calendar.SECOND, 100);

       String token = JWT.create()
                .withHeader(new HashMap<>()) //Header
                .withClaim("userID", 21) //payload
                .withClaim("username", "jingyu")
                .withExpiresAt(calendar.getTime()) //过期时间
                .sign(Algorithm.HMAC256("!34ADAS")); //签名用的secret
        System.out.println("token =" + token);
    }
    //2. 解析JWT字符串
    @Test
    public void testResolveToken(){
        //创建解析对象，使用的算法和secret要和创建token时的保持一致
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!34ADAS")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2NjU0ODA3NjAsInVzZXJJRCI6MjEsInVzZXJuYW1lIjoiamluZ3l1In0.ZIgoQJ_vm5EMzsJcxmGTR25aRpuPqucsjLh_CTfdy6Q");
        //获取解析之后payload中的信息
        Claim userID = decodedJWT.getClaim("userID");
        Claim username = decodedJWT.getClaim("username");
        System.out.println(userID.asInt());
        System.out.println(username.asString());
        //输出超时时间
        System.out.println(decodedJWT.getExpiresAt().getTime());
    }

    @Test
    public void testDemo(){
        String a = "11";
        String b = new String("22");
        System.out.println(a.equals(b));
    }
}
