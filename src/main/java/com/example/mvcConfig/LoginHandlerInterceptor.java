package com.example.mvcConfig;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName 登录拦截器
 * 拦截器只能对Controller请求进行拦截，对其他的直接访问静态资源的请求无法拦截处理
 * @Author fhb
 * @Date 2020/2/10 22:24
 * @Version 1.0
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {

//    重写前置处理方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("重写前置处理方法");
        //登录成功后应该有用户的session,这个在登录判定成功时就应设置好
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser==null){  //没有登录
            System.out.println("登录失败！");
            //下面的转发方法中的参数是url,而不是页面的地址
//            request.getRequestDispatcher("/login.html").forward(request,response);//转发到登录页,url地址栏不会变
            response.sendRedirect("/login.html"); //重定向，跳转到登录页的请求
            return false; // 返回false:表示结束执行，往下就不在执行任何方法
        }else{
            System.out.println("登录成功！");
            return true;  //返回true:继续往下执行
        }
    }
}
