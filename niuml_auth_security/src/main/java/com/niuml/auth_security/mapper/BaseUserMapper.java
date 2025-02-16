package com.niuml.auth_security.mapper;


import com.niuml.common.core.entity.BaseUser;
import feign.Param;
import org.apache.ibatis.binding.jpa.JpaTable;

/**
* @author niumengliang
* @description 针对表【ef_base_user(用户基础信息)】的数据库操作Mapper
* @createDate 2024-12-11 17:00:13
* @Entity generator.domain.EfBaseUser
*/
@JpaTable("base_user")
public interface BaseUserMapper {

    BaseUser findById(@Param("id") Integer baseUserId);
}




