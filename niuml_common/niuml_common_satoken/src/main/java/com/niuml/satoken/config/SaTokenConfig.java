package com.niuml.satoken.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/***
 * @author niumengliang
 * Date:2025/1/23
 * Time:11:06
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {


    @Value("${sa-token.excludeUrls}")//:public/**
    private List<String> excludeUrls;


    /**
     * 注册 Sa-Token 拦截器打开注解鉴权功能
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("excludeUrls: " + excludeUrls);
        // 注册 Sa-Token 拦截器打开注解鉴权功能
        registry.addInterceptor(new SaInterceptor(handler -> {
                    SaRouter.match("/**").check(StpUtil::checkLogin);
                })).addPathPatterns("/**")
                .excludePathPatterns(excludeUrls);
    }

    /**
     * Sa-Token 整合 jwt
     */
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }
}
