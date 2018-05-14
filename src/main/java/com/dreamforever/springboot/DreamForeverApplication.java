package com.dreamforever.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement //开启事物注解
@EnableAutoConfiguration
@MapperScan("com.dreamforever.springboot.dao") //扫描．mapper指向的dao

public class DreamForeverApplication {

	public static void main(String[] args) {
		SpringApplication.run(DreamForeverApplication.class, args);
	}
}
