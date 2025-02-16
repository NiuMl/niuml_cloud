package com.niuml.auth_security.config;

import com.niuml.auth_security.sms.SmsAuthenticationFilter;
import com.niuml.auth_security.sms.SmsAuthenticationProvider;
import com.niuml.auth_security.userName.UserNameAuthenticationFilter;
import com.niuml.auth_security.userName.UserNameAuthenticationProvider;
import com.niuml.security.config.WebSecurityConfig;
import com.niuml.security.handler.common.LoginFailHandler;
import com.niuml.security.handler.common.LoginSuccessHandler;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.List;

/***
 * @author niumengliang
 * Date:2025/2/16
 * Time:11:27
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebConfig  {
//    public WebConfig(ApplicationContext applicationContext) {
//        super(applicationContext);
//    }

    @Resource
    private WebSecurityConfig webSecurityConfig;

    @Resource
    private  ApplicationContext applicationContext;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain loginFilterChain(HttpSecurity http) throws Exception {
        webSecurityConfig.commonHttpSetting(http);
        http
                .securityMatcher("/public/**","/user/login/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll());

        LoginSuccessHandler loginSuccessHandler = applicationContext.getBean(LoginSuccessHandler.class);
        LoginFailHandler loginFailHandler = applicationContext.getBean(LoginFailHandler.class);
        // 加一个登录方式。用户名、密码登录
        UserNameAuthenticationFilter usernameLoginFilter = new UserNameAuthenticationFilter(
                new AntPathRequestMatcher("/user/login/username", HttpMethod.POST.name()),
                new ProviderManager(
                        List.of(applicationContext.getBean(UserNameAuthenticationProvider.class))),
                loginSuccessHandler,
                loginFailHandler);
        http.addFilterBefore(usernameLoginFilter, UsernamePasswordAuthenticationFilter.class);
        // 加一个登录方式。短信验证码 登录
        SmsAuthenticationFilter smsLoginFilter = new SmsAuthenticationFilter(
                new AntPathRequestMatcher("/user/login/sms", HttpMethod.POST.name()),
                new ProviderManager(
                        List.of(applicationContext.getBean(SmsAuthenticationProvider.class))),
                loginSuccessHandler,
                loginFailHandler);
        http.addFilterBefore(smsLoginFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
