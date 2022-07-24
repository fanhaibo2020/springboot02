package com.example.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.utils.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName JWTInterceptor
 * @Author fhb
 * @Date 2021/3/29 21:52
 * @Version 1.0
 **/
public class JWTInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        //获取请求头中的token
        String token = request.getHeader("token");
        Map<String,Object> map = new HashMap<>();
        try{
            DecodedJWT verify = JWTUtils.getTokenInfo(token);
            map.put("state",true);
            map.put("msg","请求成功！");
            return true; //放行请求
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("msg","无效签名！");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期！");
        }catch(AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","token算法不一致！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","token无效！");
        }
        map.put("state",false);
        //将结果响应到前端
        //将map转为json jackson
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(json);
        return false;
    }
}
