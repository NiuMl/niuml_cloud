package com.niuml.auth_security.mapper;

import com.niuml.auth_security.entity.LoginModeDefault;
import org.apache.ibatis.binding.jpa.JpaTable;

/**
* @author niumengliang
* 表【ef_login_mode_default(默认登录方式)】的数据库操作Mapper
* createDate  2024-12-11 17:00:13
* Entity  generator.domain.EfLoginModeDefault
 */
@JpaTable("login_mode_default")
public interface LoginModeDefaultMapper {

    //根据用户名查询登录方式
    LoginModeDefault findByUserName(String username);

    LoginModeDefault findByBaseUserIdAndUserName(int i, String username);
}




