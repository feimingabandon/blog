package com.zou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration //配置类
@EnableSwagger2// 开启Swagger2的自动配置
public class SwaggerConfig {

    //配置文档信息
    private ApiInfo apiInfo() {
        Contact contact = new Contact("邹飞鸣", "https://blog.csdn.net/qq_43483251", "1160653906@qq.com");
        return new ApiInfo(
                "Swagger", // 标题
                "配置Swagger", // 描述
                "v1.0", // 版本
                "https://blog.csdn.net/qq_43483251", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>()// 扩展
        );
    }


    @Bean // 将上面的东西注册并配置扫描接口(就是扫描包下的类是否使用了Docket注解，则可将配置了的方法显示在页面上，方便测试)
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("邹飞鸣") // 配置分组
                .apiInfo(apiInfo())
                .select()// 通过.select()方法，去配置扫描接口,RequestHandlerSelectors配置如何扫描接口
                .apis(RequestHandlerSelectors.basePackage("com.zou.controller"))
                .build();
    }


}
