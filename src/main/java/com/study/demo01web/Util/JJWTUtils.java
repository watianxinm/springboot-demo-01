package com.study.demo01web.Util;

import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class JJWTUtils {

    //token 时效
    public static final long EXPIRE = 1000*60*60*24;
    //签名哈希的密钥，对于不同的加密算法来说含义不同
    public static final String APP_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";

    /**
     * 根据用户ID和昵称生成Token
     *
     * @param id 用户ID
     * @param nickname 用户昵称
     * @return JWT规则生成的Token
     */
    public static String getJJWTToken(String id, String nickname){
        String token = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("baobao-User")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))
                .claim("id", id)
                .claim("nackname", nickname)
                //HS256算法实际就是MD5加盐值，此时APP_SECRET就代表盐值
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
        return token;
    }

    /**
     * 判断Token是否存在与有效
     *
     * @param token token字符串
     * @return 如果token有效返回TRUE，无效返回FALSE
     */
    public static boolean checkToken(String token){
        if(StringUtils.isEmpty(token)) return false;
        try{
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJwt(token);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断Token是否存在与有效
     *
     * @param request Http请求对象
     * @return 如果token有效返回TRUE，无效返回FALSE
     *
     */
    public static boolean checkToken(HttpServletRequest request){
        try {
            //从http请求中获取token字符串
            String JWTToken = request.getHeader("token");
            //判断JWTToken是否为空
            if (StringUtils.isEmpty(JWTToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(JWTToken);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据token获取会员id
     *
     * @param request Http请求对象
     * @return 解析token后获得的用户id
     */
    public static String getIdByJWTToken(HttpServletRequest request){
        String JWTToken = request.getHeader("token");
        if(StringUtils.isEmpty(JWTToken)) return "";
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(JWTToken);
        String id = claimsJws.getBody().getId();
        return id;
    }
}
