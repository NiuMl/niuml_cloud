package com.niuml.auth_security.provider;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/***
 * @author niumengliang
 * Date:2025/2/16
 * Time:11:10
 */
public class PersonProvider {
    protected List<SimpleGrantedAuthority> handDbPre(List<String> authorities) {
        List<SimpleGrantedAuthority> sga = new ArrayList<>();
        if (authorities != null) {
            for (String role : authorities) {
                sga.add(new SimpleGrantedAuthority(role));
            }
        }
        return sga;
    }
}
