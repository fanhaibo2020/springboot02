package com.example.bo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassName AsyncBO
 * @Author fhb
 * @Date 2021/4/24 21:24
 * @Version 1.0
 **/
@Slf4j
@Service
public class AsyncBO {

    @Autowired
    AsyncTest asyncTest;

//    异步方法和调用方法一定要**** 写在不同的类中 ****,如果写在一个类中则不生效
    public void async() throws Exception{
        System.out.println("----- 开始执行 -----");
        asyncTest.asyncOne();
        asyncTest.asyncTwo();
        asyncTest.asyncThree();
        asyncTest.asyncfour();
        System.out.println("----- 结束执行 -----");
    }

}
