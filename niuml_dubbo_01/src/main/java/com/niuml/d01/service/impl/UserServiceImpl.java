package com.niuml.d01.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.niuml.interfaces.entity.User;
import com.niuml.interfaces.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/***
 * @author niumengliang
 * Date:2025/2/13
 * Time:17:14
 */
@Log4j2
@DubboService
public class UserServiceImpl implements UserService {
    @Override
    public void login(String username) {
        log.info("用户登录:{}",username);
        System.out.println("[Impl] Token值：" + StpUtil.getTokenValue());
        System.out.println("[Impl] 是否登录：" + StpUtil.isLogin());
        StpUtil.login(username);
        System.out.println("[Impl] Token值：" + StpUtil.getTokenValue());
        System.out.println("[Impl] 是否登录：" + StpUtil.isLogin());
    }

    @Override
    public void save(User user) {
        log.info("保存用户信息:{}",user);
    }

    @Override
    public User getUserById(int id) {
        log.info("根据id获取用户信息:{}",id);
        return new User(id,"张三","123456","123@123.com","12341234123");
    }
}
