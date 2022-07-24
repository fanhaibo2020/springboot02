package com.example;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class Springboot02ApplicationTests {
    @Test
    void contextLoads() {
        Map<String,Object> map = new HashMap<>();
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.SECOND,60);
        String token = JWT.create()
//                .withHeader(map)  // header 可以省略
                .withClaim("userId",21)  //payload
                .withClaim("userName","张三")
                .withExpiresAt(instance.getTime())   //指定令牌过期时间
                .sign(Algorithm.HMAC256("!fagsd34t5sf")); //签名 ,秘钥是存储在服务端的,不可泄露
        System.out.println("token="+token);
    }

    @Test
    public void test(){
        //创建验证对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("!fagsd34t5sf")).build();
        //验证token
        DecodedJWT verify = jwtVerifier.verify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6IuW8oOS4iSIsImV4cCI6MTYxNjM0MTc1MCwidXNlcklkIjoyMX0.BD7QXruDpauKqy2CTVFvav6q29Z9IHamzJM5xX9iggo");//括号中为生成的token
        System.out.println(verify.getClaim("userId").asInt()); // 打印结果：21
        System.out.println(verify.getClaim("userName").asString()); //打印结果：张三
        System.out.println("过期时间："+verify.getExpiresAt());
    }

}
