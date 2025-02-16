package com.niuml.d02.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.niuml.common.core.domain.R;
import com.niuml.interfaces.entity.User;
import com.niuml.interfaces.service.UserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/***
 * @author niumengliang
 * Date:2025/2/13
 * Time:17:19
 */
@RequestMapping("/public")
@RestController
public class UserController {

    @DubboReference
    private UserService userService;

    @GetMapping("/login/{username}")
    public R<String> login(@PathVariable("username") String username){
        System.out.println("[UserController] Token值：" + StpUtil.getTokenValue());
        System.out.println("[UserController] 是否登录：" + StpUtil.isLogin());
        userService.login(username);
        System.out.println("[UserController] Token值：" + StpUtil.getTokenValue());
        System.out.println("[UserController] 是否登录：" + StpUtil.isLogin());
        return R.ok();
    }

    @GetMapping("/user/{id}")
    public R<User> getUser(@PathVariable("id") Integer id){
        User userById = userService.getUserById(id);
        return R.ok(userById);
    }

    @PostMapping("/user")
    public R<String> save(@RequestBody User user){
        userService.save(user);
        return R.ok();
    }
}
