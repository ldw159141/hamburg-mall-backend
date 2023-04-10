package com.ldw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author HP刘德伟
 */
@SpringBootApplication
@MapperScan("com.ldw.mapper")
@EnableSwagger2
@ComponentScan(basePackages = "com.ldw.controller")
@ComponentScan(basePackages = "com.ldw.service")
public class LdwApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdwApplication.class, args);
	}

}
