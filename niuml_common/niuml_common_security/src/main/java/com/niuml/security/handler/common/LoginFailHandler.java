package com.niuml.security.handler.common;

import com.niuml.common.core.domain.R;
import com.niuml.common.core.domain.ResultBuilder;
import com.niuml.common.core.utils.ResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.niuml.common.core.constant.Constants.FAIL;


/***
 * @author niumengliang
 * Date:2024/12/11
 * Time:15:38
 * 登录失败处理器
 */
@Log4j2
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("登录失败: {}", exception.getMessage());
        R responseData = ResultBuilder.initResult()
                .data(null)
                .code(FAIL)
                .msg(exception.getMessage())
                .build();
        ResponseUtil.send(response,responseData);
    }
}
