package com.example.mvcConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 扩展springMVC的配置类
 * @Author fhb
 * @Date 2020/2/10 21:38
 * @Version 1.0
 **/

@Configuration
//该注解作用同@Controller、@Service、@Respority，通知spring容器来接管这个类
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
/**
 * 添加视图控制器
 */
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("进入视图解析器");
//        registry.addViewController("/").setViewName("/login/login");
//        registry.addViewController("/login.html").setViewName("/login/login");
    }

    /**
     * 添加拦截器
     * 每一次请求到来的时候，拦截器都会执行一次拦截(这个很重要)
     * 比如这里先执行addInterceptors()方法，在执行其中的 LoginHandlerInterceptor()类中的方法
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(new LoginHandlerInterceptor()).  //对拦截器类进行拦截
               addPathPatterns("/**").   // 对所有请求进行被拦截(两个**表示所有的)
               excludePathPatterns("/login.html","/login.do","/css/**","/images/**","/plugins/**","/login/**","/index/**");  //这里是排除不被拦截的请求和静态资源文件
    }
}
