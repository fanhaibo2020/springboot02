package com.example.aop.Aspect;

import java.lang.annotation.*;

/**
 * 系统日志（自定义注解）
 *
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    String opt();   //操作
}