package com.example.transaction;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.entity.Bank;
import com.example.entity.User;
import com.example.mapper.BankMapper;
import com.example.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName testTransaction
 * @Describle spring事务
 * @Author fhb
 * @Date 2020/11/1 22:12
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest()
@Slf4j
public class testTransaction {

    @Autowired
    UserMapper userMapper;
    @Autowired
    BankMapper bankMapper;

    //junit测试
    @Test
    @Transactional
//    @Rollback(false)//防止事务自动回滚
    public void abc() throws Exception{
//        serviceA(); //向A表更新数据
//        serviceB(); //向B表插入数据
//        try{
//            int a = 1/0;
//        }catch (Exception e){
//            log.error("errormsg:{}",e.getMessage());
//        }
    }




}
