package com.niuml.d02;

import cn.dev33.satoken.SaManager;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableDubbo
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class D02Application {

	public static void main(String[] args) {
		SpringApplication.run(D02Application.class, args);
		System.out.println("\n启动成功：Sa-Token配置如下：" + SaManager.getConfig());
	}

}
