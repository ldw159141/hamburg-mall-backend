package com.ldw.configuration;

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
 * @author HP刘德伟
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {


//        /**
//         * 创建Docket类型的对象。并使用spring容器管理。
//         * Docket是Swagger中的全局配置对象。
//         * @return docket
//         */
        @Bean
        public Docket createRestApi() {
            return  new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.ldw.controller"))
                    .paths(PathSelectors.any())
                    .build().apiInfo(new ApiInfoBuilder().title("hamburgmall").version("v1.0").build());

    }


//    @Bean
//    public Docket docket() {
//        Docket docket=new Docket(DocumentationType.SWAGGER_2);
//        ApiInfo apiInfo=new ApiInfoBuilder().contact(
//                new Contact("123","http://www.baidu.com","1244943676@qq.com")
//        ).build();
//        docket.apiInfo(apiInfo);
//
//        return  docket;
//    }
//@Bean
//public Docket createRestApi() {
//    return new Docket(DocumentationType.SWAGGER_2)
//            .apiInfo(apiInfo())
//            .select()
//            .apis(RequestHandlerSelectors.basePackage("com.ldw.controler"))    //这个是重点
//            .paths(PathSelectors.any())
//            .build();
//}
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("接口文档标题")
//                .description("文档内容描述")
//                .termsOfServiceUrl("http://www.baidu.com")
//                .version("1.0")
//                .build();
//    }

}
