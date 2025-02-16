package com.niuml.test.config;

import com.niuml.security.config.WebSecurityConfig;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/***
 * @author niumengliang
 * Date:2025/2/16
 * Time:11:27
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebConfig {
//    public WebConfig(ApplicationContext applicationContext) {
//        super(applicationContext);
//    }

    @Resource
    private WebSecurityConfig webSecurityConfig;

    @Bean
    public SecurityFilterChain testFilterChain(HttpSecurity http) throws Exception {
        webSecurityConfig.commonHttpSetting(http);
        http
                .securityMatcher("/public/**","/user/login/**")
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll());
        return http.build();
    }
}
