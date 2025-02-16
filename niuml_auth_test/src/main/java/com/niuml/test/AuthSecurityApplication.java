package com.niuml.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthSecurityApplication.class, args);
		System.out.println("启动成功" );
	}

}
