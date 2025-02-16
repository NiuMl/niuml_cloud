package com.niuml.security.handler.exception;

import com.niuml.common.core.domain.R;
import com.niuml.common.core.domain.ResultBuilder;
import com.niuml.common.core.utils.ResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

/**
 * 认证失败时，会执行这个方法。将失败原因告知客户端
 */
public class CustomAuthenticationExceptionHandler implements
    AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    R r = ResultBuilder.initResult().msg("${authentication.fail:认证失败}").build();
    ResponseUtil.send(response,r);
  }
}
