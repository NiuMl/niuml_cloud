package com.niuml.auth_security.sms;

import com.niuml.security.handler.common.UserLoginInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/***
 * @author niumengliang
 * Date:2024/12/12
 * Time:10:50
 * sms使用的token
 */
@Getter
@Setter
public class SmsAuthentication extends AbstractAuthenticationToken {
    //电话号
    private String phone;
    //验证码
    private String code;
    //当前用户
    private UserLoginInfo currentUser;

    public SmsAuthentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    public SmsAuthentication() {
        super(null);
    }

    @Override
    public Object getCredentials() {
        return isAuthenticated() ? null : code;
    }

    @Override
    public Object getPrincipal() {
        return isAuthenticated() ? currentUser : phone;
    }
}
