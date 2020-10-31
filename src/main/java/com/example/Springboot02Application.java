package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//        (exclude= {DataSourceAutoConfiguration.class})
@EnableAspectJAutoProxy
@EnableScheduling  //开启定时任务
@MapperScan(basePackages = {"com.example.mapper.**"})
public class Springboot02Application {

    public static void main(String[] args) {
        SpringApplication.run(Springboot02Application.class, args);
    }

}
