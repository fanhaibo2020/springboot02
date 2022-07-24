package com.example.bo;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @ClassName AsyncTest
 * @Author fhb
 * @Date 2021/4/24 22:00
 * @Version 1.0
 **/
@Component
public class AsyncTest {

    @Async
    public void asyncOne() throws Exception{
        System.out.println("开始执行One");
        for (int i=0;i<100;i++){
            System.out.println("One["+i+"],线程名："+Thread.currentThread().getName());
        }
        System.out.println("结束执行One");
    }

    @Async
    public String asyncTwo() throws Exception{
        System.out.println("开始执行Two");
        for (int i=0;i<100;i++){
            System.out.println("Two["+i+"],线程名："+Thread.currentThread().getName());
        }
        System.out.println("结束执行Two");
        return "aaaaa";
    }

    @Async
    public void asyncThree() throws Exception{
        System.out.println("开始执行Three");
        for (int i=0;i<100;i++){
            System.out.println("Three["+i+"],线程名："+Thread.currentThread().getName());
        }
        System.out.println("结束执行Three");
    }

    @Async
    public void asyncfour() throws Exception{
        System.out.println("开始执行four");
        for (int i=0;i<100;i++){
            System.out.println("four["+i+"],线程名："+Thread.currentThread().getName());
        }
        System.out.println("结束执行four");
    }

}
