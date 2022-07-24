package com.example.config;

import com.example.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName InterceptorConfig
 * @Author fhb
 * @Date 2021/3/29 22:17
 * @Version 1.0
 **/
//先注释,后期记得放开
//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())  //添加拦截器
                .addPathPatterns("/**")  //拦截所有路径(对其他所有请求进行拦截，在请求头中携带token验证 )
                .excludePathPatterns("/user/token/login.do");  //排除路径:所有登录用户放行(不然没法生成token)
    }
}
