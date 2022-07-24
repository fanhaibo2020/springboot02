package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.entity.Bank;
import com.example.entity.User;
import com.example.mapper.BankMapper;
import com.example.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TranctionalController
 * @Description 事务管理测试类
 * @Author fhb
 * @Date 2020/12/6 17:43
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/transaction")
@Api(value="Spring事务",tags = "Spring事务测试相关接口")
public class TranctionalController {

    @Autowired
    UserMapper userMapper;
    @Autowired
    BankMapper bankMapper;

    @PostMapping("testTransaction.do")
    @ApiOperation("测试事务1")
    @Transactional
    public void abc() throws Exception{
        serviceA(); //向A表更新数据
        //使用AopContext获取当前对象的动态代理
        TranctionalController currentProxy = (TranctionalController)AopContext.currentProxy();
        try{
            currentProxy.serviceB(); //向B表插入数据
        }catch(Exception e){
            log.error("e:{}",e.getMessage());
//            throw new Exception("e:"+e.getMessage());
        }
    }

    //修改Uese表的数据
//    @Transactional
    public void serviceA(){
        LambdaUpdateWrapper<User> lambdaUpdate = new LambdaUpdateWrapper<>();
        lambdaUpdate.eq(User::getUserName,"李四").set(User::getAge,"55");
        int rows = userMapper.update(null,lambdaUpdate);
        System.out.println("修改rows="+rows);
    }

    //插入数据
    @Transactional
    public void serviceB(){
        Bank bank = new Bank();
        bank.setUserName("JackMa");
        bank.setUserUuid("60");
        bank.setMoney("68.8");
        int row = bankMapper.insert(bank);
        System.out.println("添加数据影响行数="+row);
        throw new RuntimeException("人为制造异常并抛出");
    }


}
