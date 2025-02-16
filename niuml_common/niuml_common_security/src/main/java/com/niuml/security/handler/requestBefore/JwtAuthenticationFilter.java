package com.niuml.security.handler.requestBefore;

import com.niuml.common.core.exception.BaseException;
import com.niuml.common.core.utils.JwtUtil;
import com.niuml.security.handler.common.UserLoginInfo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

import static com.niuml.common.core.constant.Constants.TOKEN_MISS_ERROR;


@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @SuppressWarnings("NullableProblems")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        logger.debug("Use OpenApi1AuthenticationFilter");
        String jwtToken = request.getHeader("Authorization");
        if (!StringUtils.hasLength(jwtToken)) {
            throw new BaseException(TOKEN_MISS_ERROR,"${token.miss:无token}");
        }
        if (jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7);
        }
        UserLoginInfo userLoginInfo = JwtUtil.verifyJwt(jwtToken, UserLoginInfo.class);
        Collection<? extends GrantedAuthority> authorities = null;
        if (userLoginInfo != null) {
            authorities = userLoginInfo.getAuthorities().stream()
                    .map(SimpleGrantedAuthority::new).toList();
        }
        JwtAuthentication authentication = new JwtAuthentication(authorities);
        authentication.setJwtToken(jwtToken);
        authentication.setAuthenticated(true); // 设置true，认证通过。
        authentication.setCurrentUser(userLoginInfo);
        // 认证通过后，一定要设置到SecurityContextHolder里面去。
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 放行
        filterChain.doFilter(request, response);
    }
}
