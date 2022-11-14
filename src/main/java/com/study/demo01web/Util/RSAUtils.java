package com.study.demo01web.Util;

import cn.hutool.crypto.asymmetric.RSA;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Map;

/**
 * java-jwt
 * 非对称密钥
 *
 * 静态方法：
 * 静态方法不需要通过它所属的类的任何实例就可以被调用，因此在静态方法中不能使用 this 关键字，也不能直接访问所属类的实例变量和实例方法，
 * 但是可以直接访问所属类的静态变量和静态方法。
 * 另外，和 this 关键字一样，super 关键字也与类的特定实例相关，所以在静态方法中也不能使用 super 关键字。
 */
public class RSAUtils {

    //指定生成签名的私钥和解析签名的公钥
    private static final String RSA_PRIVATE_KEY = "...";
    private static final String RSA_PUBLIC_KEY = "...";

    //1. 生成 JWT Token
    public static String getRSAToken(Map<String, String> payload){
        //指定token过期时间为7天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 7);
        //创建JWT对象
        JWTCreator.Builder builder = JWT.create();
        //解析传入参数，构建payload
        //payload.forEach((k, v) -> builder.withClaim(k, v));
        payload.forEach(builder::withClaim);
        //利用hutool创建RSA
        RSA rsa = new RSA(RSA_PRIVATE_KEY, null);
        //获取私钥
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) rsa.getPrivateKey();
        //生成Token字符串，传入私钥和过期时间
        String token =
                builder.withExpiresAt(calendar.getTime())
                .sign(Algorithm.RSA256(null, rsaPrivateKey));
        return token;
    }
    //2. 解析 JWT Token
    public static DecodedJWT getDecodedRSA(String token){
        //利用hutool创建RSA
        RSA rsa = new RSA(null, RSA_PUBLIC_KEY);
        //获取公钥
        RSAPublicKey rsaPublicKey = (RSAPublicKey) rsa.getPublicKey();
        //验签时传入公钥
        JWTVerifier jwtVerifier = JWT.require(Algorithm.RSA256(rsaPublicKey, null)).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT;
    }
}
