package com.example.quartz;

import org.junit.Test;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 通过注解实现任务调度
 * @Author fhb
 * @Date 2020/6/1 23:39
 **/
@Component  //将该类交给spring托管
//在主启动类上加注解 @EnableScheduling
public class ScheduledTaskOne {

//    @Scheduled(cron="*/10 * * * * ? ")  //每十秒执行一次,先注释,要用时再放开注解
    public void sendMQ(){
        System.out.print("正在执行定时任务 ====");
        System.out.println("当前时间："+ new Date());
    }

}
