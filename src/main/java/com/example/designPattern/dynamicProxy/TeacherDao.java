package com.example.designPattern.dynamicProxy;

/**
 * @ClassName TeacherDao
 * @Author fhb
 * @Date 2020/5/8 14:01
 * @Version 1.0
 **/
public class TeacherDao implements ITeacherDao {

    @Override
    public void teach() {
        System.out.println("老师正在授课中。。。");
    }

    @Override
    public void sayHello(String name) {
        System.out.println("sayHello:"+name);
    }
}
