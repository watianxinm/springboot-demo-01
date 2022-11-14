package com.study.demo01web.Util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class NewVersJJWTUtils {

    //token时效
    public static final long EXPIRE = 1000*60*60*24;
    //签名哈希的密钥，对于不同的加密算法来说含义不同
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHOsdadasdasfdssfeweee";

    /**
     * 根据用户id和昵称生成token
     * @param id 用户id
     * @param nackname 用户昵称
     * @return JWT规则生成的token
     */
    public static String getToken(String id, String nackname){
        String token = Jwts.builder()
                .setSubject("baobao-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("id", id)
                .claim("nackname", nackname)
                //传入Key对象
//                .signWith(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8)),SignatureAlgorithm.HS256)
                .signWith(SignatureAlgorithm.HS256, Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8)))
                .compact();
        return token;
    }

    /**
     * 解码token
     * @param token token字符串
     * @return 返回token字符串
     */
    public static Jws<Claims> decodeToken(String token){
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(APP_SECRET.getBytes(StandardCharsets.UTF_8))).parseClaimsJws(token);
        return claimsJws;
    }
}
