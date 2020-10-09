package com.example.aop.Aspect;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class SystemLogAspect  {

    //切点是连接点的集合,代表了一组连接点,表示所有含有SystenLog类上注解属性的都是切点
//    @Pointcut("@annotation(com.example.aop.Aspect.SystemLog)")
    @Pointcut("@annotation(com.example.aop.Aspect.SystemLog)")
    public void methodPointCut() {
    }

    @Around("methodPointCut()")
    public Object printLog(ProceedingJoinPoint point) throws Exception {
//        Object proceed = null;
//        try{
//            point.proceed();   //为什么捕获异常了还报错？？？
//        }catch(Exception e){
//            System.out.println(e.getMessage());
//        }
        String name = point.getSignature().getName();
        Object target = point.getTarget();
        Object Signature = point.getSignature();
        System.out.println("name="+name); // addUser  方法名
        for(int i=0;i< point.getArgs().length;i++){
            System.out.println("args["+point.getArgs()[i]+"]="+point.getArgs()[i].toString());  //参数名
        }
        Class targetClass = point.getTarget().getClass();
        Method[] methods = targetClass.getMethods();
        System.out.println("methods="+methods);
        System.out.println("target="+target);  //com.example.aop.Aspect.OperatorController@5d279de2
        System.out.println("Signature="+Signature);  //void com.example.aop.Aspect.OperatorController.addUser(String)
        System.out.println("3333="+Arrays.toString(point.getArgs()));
        return null;
    }

}