package com.example.mvcConfig;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName MybatisPlusConfig
 * @Author fhb
 * @Date 2020/10/9 22:50
 * @Version 1.0
 **/
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件，自动识别数据库类型 多租户
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

}
