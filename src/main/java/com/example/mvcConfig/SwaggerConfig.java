package com.example.mvcConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SwaggerConfig
 * @Author fhb
 * @Date 2020/9/14 13:31
 * @Version 1.0
 **/
//告诉 Spring Boot 需要加载这个配置类
@Configuration
//启用 Swagger2
@EnableSwagger2
//@Profile({"dev"})
//是否开启swagger，正式环境一般是需要关闭的（避免不必要的漏洞暴露！），可根据springboot的多环境配置进行设置
//@ConditionalOnProperty(name = "swagger.enable",  havingValue = "true")
@ComponentScan(basePackages = {"com.example.controller"})
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    /**
     * 全局参数
     *
     * @return List<Parameter>
     */
    private List<Parameter> parameter() {
        List<Parameter> params = new ArrayList<Parameter>();
        params.add(new ParameterBuilder().name("token")
                .description("认证令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false).build());
        return params;
    }

    // swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(enableSwagger)    //设置开启swagger的开关
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.controller"))  //设置扫描包路径,这里不能用*吗？只能指定的具体的包吗
                .paths(PathSelectors.any())
                .build();
    }

    // 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 页面标题
                .title("mybitsplus接口文档")
                .termsOfServiceUrl("http://localhost:9090/doc.html")
                .version("1.0")
                .description("搭配MybitsPlus的API 描述")
                .build();
    }

}
