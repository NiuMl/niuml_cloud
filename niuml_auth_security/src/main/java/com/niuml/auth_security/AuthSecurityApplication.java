package com.niuml.auth_security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.niuml.auth_security.mapper")
public class AuthSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthSecurityApplication.class, args);
		System.out.println("启动成功" );
	}

}
