package com.niuml.auth.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.StpUtil;
import com.niuml.auth.entity.Input2;
import com.niuml.common.core.constant.RedisFinalKey;
import com.niuml.common.core.domain.R;
import com.niuml.common.redis.service.RedisService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/***
 * @author niumengliang
 * Date:2025/1/23
 * Time:11:33
 */
@Log4j2
@RestController
@RequestMapping("/test/")
public class LoginController {

    @Resource
    private RedisService redisService;

    @RequestMapping("public/login")
    public R<String> userNameLogin(@RequestParam("userName") String userName,
                                   @RequestParam("password") String password){
        log.info("userNameLogin userName:{},password:{}",userName,password);
        StpUtil.login(999);
        List<String> list = new ArrayList<String>();
		list.add("101");
		list.add("user-add");
		list.add("user-delete");
		list.add("user-update");
		list.add("user-get");
		list.add("article-get");
        redisService.setCacheList(RedisFinalKey.PR+999,list);
        return R.ok("登录成功");
    }

    @RequestMapping("test")
    public R<String> test(){
        return R.ok("test");
    }

    @SaCheckPermission("user-add")
    @RequestMapping("test2")
    public R<String> test2(){
        return R.ok("test");
    }
    @RequestMapping("public/test")
    public R<String> test2(@Valid Input2 input){
        log.info("test2:{}",input);
        return R.ok("test");
    }
}
