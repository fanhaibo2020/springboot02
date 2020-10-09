package com.example.designPattern.cglibProxy;

/**
 * @ClassName TeacherDao
 * @Author fhb
 * @Date 2020/5/8 14:00
 * @Version 1.0
 **/
public class TeacherDao {
    public void teach(){
        System.out.println("老师授课中，我是cglib代理，不需要实现接口");
    }
}
