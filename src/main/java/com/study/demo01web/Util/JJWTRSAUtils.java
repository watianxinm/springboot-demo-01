package com.study.demo01web.Util;

import cn.hutool.crypto.asymmetric.RSA;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

public class JJWTRSAUtils {

    private static final String RSA_PRIVATE_KEY = "...";
    private static final String RSA_PUBLIC_KEY = "...";
    private static final long EXPIRE = 1000*60*60*24;

    /**
     * 根据用户id和昵称nackname生成token
     * @param id 用户id
     * @param nackname 用户昵称
     * @return JWT规则生成的token
     */
    public static String getRSAToken(String id, String nackname){
        //利用hutool创建RSA
        RSA rsa = new RSA(RSA_PRIVATE_KEY, null);
        RSAPrivateKey privateKey = (RSAPrivateKey) rsa.getPrivateKey();
        String token = Jwts.builder()
                .setSubject("baobao-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("id", id)
                .claim("nackname", nackname)
                //签名指定私钥
                .signWith(SignatureAlgorithm.HS256, privateKey)
                .compact();
        return token;
    }

    /**
     * 解析token字符串
     * @param token token字符串
     * @return 返回
     */
    public static Jws<Claims> decodeToken(String token){
        RSA rsa = new RSA(null, RSA_PUBLIC_KEY);
        RSAPublicKey publicKey = (RSAPublicKey) rsa.getPublicKey();
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
        return claimsJws;
    }
}
