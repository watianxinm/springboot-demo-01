package com.study.demo01web.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

/**
 * java-jwt
 * 对称秘钥
 */
public class JWTUtils {
    //指定签名密钥
    private static final String SECRET  = "!DAR$";

    //1. 生成token字符串
    public static String getToken(Map<String, String> payload){
        //指定token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);

        JWTCreator.Builder builder = JWT.create();
        /**
         * 构建payload
         *
         * “->”是Java 8新增的Lambda表达式中，变量和临时代码块的分隔符，即：
         * (变量)->{代码块}
         * 如果代码块只有一个表达式，大括号可以省略。如果变量类型可以自动推断出来，可以不写变量类型。
         */
        payload.forEach(builder::withClaim);
        //指定过期时间和签名算法
        String token = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    //2. 解析token字符串
    public static DecodedJWT decodeToken(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT;
    }
}
