package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName HelloController
 * @Author fhb
 * @Date 2020/2/9 19:45
 * @Version 1.0
 **/

@Controller
public class HelloController {
    @GetMapping("/hello.do")
    @ResponseBody  //该注解使方法不走视图解析器，页面上直接返回“hello world!”
    public String hello(){
        System.out.println("我是hello");
        return "hello world!";
    }
}
