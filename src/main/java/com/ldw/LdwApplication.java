package com.ldw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author HP刘德伟
 */
@SpringBootApplication
@MapperScan("com.ldw.mapper")
@EnableSwagger2
@EnableScheduling
@EnableCaching
public class LdwApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LdwApplication.class, args);
	}

	//配置过滤器
//     @Bean
//     public FilterRegistrationBean filterRegistrationBean(){
//		         FilterRegistrationBean filterFilterRegistrationBean = new FilterRegistrationBean( new UrlFilter());
//		         filterFilterRegistrationBean.addUrlPatterns("/*");
//		         return filterFilterRegistrationBean;
//		     }

}
