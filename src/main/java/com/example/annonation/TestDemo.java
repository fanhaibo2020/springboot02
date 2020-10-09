package com.example.annonation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ClassName TestDemo
 * @Author fhb
 * @Date 2020/5/1 0:00
 * @Version 1.0
 **/
public class TestDemo {

    public static void main(String[] args) throws Exception{
        //获取类上的注解
        Class<Demo> clazz = Demo.class;
        MyAnnonation one = clazz.getAnnotation(MyAnnonation.class);
        System.out.println(one.getValue()+","+one.intValue()); //打印结果：在类上的注解,0

        //获取成员变量上的注解
        Field name = clazz.getField("name");
        MyAnnonation two = name.getAnnotation(MyAnnonation.class);
        System.out.println(two.getValue()+","+two.intValue());//打印结果：字段上的注解,8

        //获取hello方法上的注解
        Method hello = clazz.getMethod("hello",null);
        MyAnnonation three = hello.getAnnotation(MyAnnonation.class);
        System.out.println(three.getValue()+","+three.intValue()); //打印结果：方法上的注解,5

        //获取defaultMethod方法上的注解
        Method defaultMethod = clazz.getMethod("defaultMethod",null);
        MyAnnonation four = defaultMethod.getAnnotation(MyAnnonation.class);
        System.out.println(four.getValue()+","+four.intValue()); //打印结果：默认没有描述,0
    }

}
