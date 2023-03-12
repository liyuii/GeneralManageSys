package com.web.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger的配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包路径,控制器类包
                .apis(RequestHandlerSelectors.basePackage("com.web.ManageModule.controller"))
//                .apis(Predicates.or(
//                        RequestHandlerSelectors.basePackage("com.web.CommunicateModule.controller"),
//                        RequestHandlerSelectors.basePackage("com.web.ExcelOptModule.controller"),
//                        RequestHandlerSelectors.basePackage("com.web.ManageModule.controller"),
//                        RequestHandlerSelectors.basePackage("com.web.NetModule.controller"),
//                        RequestHandlerSelectors.basePackage("com.web.WechatModule.controller")
//                        ))
                .paths(PathSelectors.any())
                .build();
    }
    //构建 api文档的详细信息函数,注意这里的注解引用的是哪个
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Spring Boot 集成 Swagger2 测试接口文档")
                //创建人
                .contact(new Contact("lyf", "http://www.baidu.com", "xxxxxxxxxx@qq.com"))
                //版本号
                .version("1.0")
                //描述
                .description("API 描述")
                .build();
    }
}
