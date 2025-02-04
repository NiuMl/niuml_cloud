package com.niuml.auth;

import cn.dev33.satoken.SaManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
		System.out.println("\n启动成功：Sa-Token配置如下：" + SaManager.getConfig());
	}

}
