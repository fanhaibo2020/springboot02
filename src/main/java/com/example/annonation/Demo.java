package com.example.annonation;

/**
 * 使用注解demo
 * @Author fhb
 * @Date 2020/4/30 23:53
 * @Version 1.0
 **/
@MyAnnonation(getValue = "在类上的注解")
public class Demo {
    @MyAnnonation(intValue = 8,getValue = "字段上的注解")
    public String name;

    @MyAnnonation(getValue = "方法上的注解",intValue = 5)
    public void hello(){}

    @MyAnnonation()
    public void defaultMethod(){}
}
