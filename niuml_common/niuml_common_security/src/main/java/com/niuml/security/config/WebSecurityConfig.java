package com.niuml.security.config;

import com.niuml.security.handler.exception.CustomAuthenticationExceptionHandler;
import com.niuml.security.handler.exception.CustomAuthorizationExceptionHandler;
import com.niuml.security.handler.exception.CustomSecurityExceptionHandler;
import com.niuml.security.handler.requestBefore.JwtAuthenticationFilter;
import jakarta.servlet.Filter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;


/***
 * @author niumengliang
 * Date:2024/12/12
 * Time:15:43
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {


    protected final ApplicationContext applicationContext;

    private final AuthenticationEntryPoint authenticationEntryPoint = new CustomAuthenticationExceptionHandler();
    private final AccessDeniedHandler accessDeniedHandler = new CustomAuthorizationExceptionHandler();
    private final Filter globalSpringSecurityExceptionHandler = new CustomSecurityExceptionHandler();

    public WebSecurityConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }



    public void commonHttpSetting(HttpSecurity http) throws Exception {
        http.formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .logout(AbstractHttpConfigurer::disable)
                .sessionManagement(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                // requestCache用于重定向，前后端分析项目无需重定向，requestCache也用不上
                .requestCache(cache -> cache
                        .requestCache(new NullRequestCache())
                )
                // 无需给用户一个匿名身份
                .anonymous(AbstractHttpConfigurer::disable);
        // 处理 SpringSecurity 异常响应结果。响应数据的结构，改成业务统一的JSON结构。不要框架默认的响应结构
        http.exceptionHandling(exceptionHandling ->
                exceptionHandling
                        // 鉴权失败异常
                        .accessDeniedHandler(accessDeniedHandler)
                        // 认证失败异常
                        .authenticationEntryPoint(authenticationEntryPoint)
        );
        // 其他未知异常. 尽量提前加载。
        http.addFilterBefore(globalSpringSecurityExceptionHandler, SecurityContextHolderFilter.class);
    }


    @Bean
    public SecurityFilterChain myApiFilterChain(HttpSecurity http) throws Exception {
        commonHttpSetting(http);
        JwtAuthenticationFilter openApi1Filter = new JwtAuthenticationFilter();
        // 加一个登录方式。用户名、密码登录
        http.addFilterBefore(openApi1Filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
