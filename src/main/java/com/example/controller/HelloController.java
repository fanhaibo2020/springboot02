package com.example.controller;

import com.example.bo.AsyncBO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sound.midi.SysexMessage;

/**
 * @ClassName HelloController
 * @Author fhb
 * @Date 2020/2/9 19:45
 * @Version 1.0
 **/

@Controller
public class HelloController {

    @Autowired
    AsyncBO asyncBO;

    @GetMapping("/hello.do")
    @ResponseBody  //该注解使方法不走视图解析器，页面上直接返回“hello world!”
    public String hello() throws Exception{
        System.out.println("我是hello");
        System.out.println(System.currentTimeMillis());
        Thread.sleep(5000);  //休眠5秒钟
        System.out.println(System.currentTimeMillis());
        return "hello world!";
    }

    @GetMapping("asyncTest.do")
    @ApiOperation("测试异步执行")
    @ResponseBody
    public String async() throws Exception{
        asyncBO.async();
        return "测试异步执行";
    }
}
