package com.niuml.auth_security.mapper;


import com.niuml.auth_security.entity.LoginModeTel;
import org.apache.ibatis.binding.jpa.JpaTable;

/**
* @author niumengliang
* 针对表【login_mode_tel(电话号登录方式)】的数据库操作Mapper
* CreateDate 2024-12-12 14:57:56
*/
@JpaTable("login_mode_tel")
public interface LoginModeTelMapper {

    LoginModeTel findByTel(String tel);

}




