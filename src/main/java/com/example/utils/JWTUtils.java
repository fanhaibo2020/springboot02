package com.example.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JWTUtils
 * @Author fhb
 * @Date 2021/3/23 23:04
 * @Version 1.0
 **/
public class JWTUtils {

    public static final String SIGN = "Tffufgwue$%bhfjhv";

    /**
     * 生成token   header.payload.sign
     * @return
     * @throws Exception
     */
    public static String getToken(Map<String,String> map) throws Exception{
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,600);
        //创建JWT builder
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())  //指定过期时间
                              .sign(Algorithm.HMAC256(SIGN)); //签名

//        String token = JWT.create()
//                .withClaim("userId",21)  //payload
//                .withClaim("userName","张三")
//                .withExpiresAt(instance.getTime())   //指定令牌过期时间
//                .sign(Algorithm.HMAC256("!fagsd34t5sf")); //签名 ,秘钥是存储在服务端的,不可泄露
        System.out.println("token="+token);
        return token;
    }

    /**
     * 验证token 合法性,获取token信息
     * @throws Exception
     */
    public static DecodedJWT getTokenInfo(String token) throws Exception{
         DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
         return verify;
    }

}
