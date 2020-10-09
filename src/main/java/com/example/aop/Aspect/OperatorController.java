package com.example.aop.Aspect;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  测试aop的入口
 **/
@Controller
@RequestMapping("aop")
public class OperatorController {


    @SystemLog(opt = "添加用户")  //对应自定义注解类SystemLog
    @RequestMapping("addUser.do")
    @ResponseBody()
    public void addUser(String userName) {
        System.out.println("添加用户="+userName);
    }
}
