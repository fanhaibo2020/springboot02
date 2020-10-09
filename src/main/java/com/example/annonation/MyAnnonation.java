package com.example.annonation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * @Author fhb
 * @Date 2020/4/30 23:50
 * @Version 1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnonation {
    String getValue() default "默认没有描述";
    int  intValue() default 0;
}
