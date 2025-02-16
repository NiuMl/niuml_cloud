package com.niuml.satoken.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.niuml.common.core.constant.RedisFinalKey;
import com.niuml.common.redis.service.RedisService;
import jakarta.annotation.Resource;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Log4j2
@Component    // 打开此注解，保证此类被springboot扫描，即可完成sa-token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {


    @Resource
    private RedisService redisService;


    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        log.info("开始赋予权限 id :{}, type:{}", loginId, loginType);
        List<String> list = redisService.getCacheList(RedisFinalKey.PR + loginId.toString());
        log.info(" id :{},权限列表:{}", loginId, list);
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
//		List<String> list = new ArrayList<String>();
//		list.add("101");
//		list.add("user-add");
//		list.add("user-delete");
//		list.add("user-update");
//		list.add("user-get");
//		list.add("article-get");
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        log.info("开始赋予角色 id :{}, type:{}", loginId, loginType);
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询角色
//        List<String> list = new ArrayList<String>();
//        list.add("admin");
//        list.add("super-admin");
        return getPermissionList(loginId, loginType);
    }

}
