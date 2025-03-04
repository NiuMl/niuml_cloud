package com.niuml.auth_security.userName;

import com.niuml.common.core.utils.RequestUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.io.IOException;
import java.util.Map;

/***
 * @author niumengliang
 * Date:2024/12/11
 * Time:16:18
 * 用户名密码登录
 * AbstractAuthenticationProcessingFilter 的实现类要做的工作：
 * 1. 从HttpServletRequest提取授权凭证。假设用户使用 用户名/密码 登录，就需要在这里提取username和password。
 *    然后，把提取到的授权凭证封装到的Authentication对象，并且authentication.isAuthenticated()一定返回false
 * 2. 将Authentication对象传给AuthenticationManager进行实际的授权操作
 */
@Log4j2
public class UserNameAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public UserNameAuthenticationFilter(AntPathRequestMatcher pathRequestMatcher,
                                        AuthenticationManager authenticationManager,
                                        AuthenticationSuccessHandler authenticationSuccessHandler,
                                        AuthenticationFailureHandler authenticationFailureHandler) {
        super(pathRequestMatcher);
        setAuthenticationManager(authenticationManager);
        setAuthenticationSuccessHandler(authenticationSuccessHandler);
        setAuthenticationFailureHandler(authenticationFailureHandler);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        log.info("当前是使用用户名和密码的Filter");
        // 提取请求数据
        Map<String, Object> requestMapData =  RequestUtil.req2Map(request);
        String username = requestMapData.get("username").toString();
        String password = requestMapData.get("password").toString();
        // 封装成Spring Security需要的对象
        UserNameAuthentication authentication = new UserNameAuthentication();
        authentication.setUsername(username);
        authentication.setPassword(password);
        authentication.setAuthenticated(false);
        // 开始登录认证。SpringSecurity会利用 Authentication对象去寻找 AuthenticationProvider进行登录认证
        return getAuthenticationManager().authenticate(authentication);
    }
}
