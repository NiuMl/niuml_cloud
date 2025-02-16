package com.niuml.test.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * @author niumengliang
 * Date:2025/2/16
 * Time:12:44
 */
@Log4j2
@RestController
public class TestController {
    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping("/test")
    public String test(){
        log.info("test");
        return "test";
    }
    @PreAuthorize("hasAuthority('user:admin')")
    @GetMapping("/testHasAuth")
    public String test11(){
        log.info("test");
        return "test";
    }
    @PreAuthorize("hasAuthority('user:admin1')")
    @GetMapping("/testHasAuth1")
    public String test111(){
        log.info("test");
        return "test";
    }
    @GetMapping("/public/test")
    public String test1(){
        log.info("public test");
        return "public test";
    }
    @GetMapping("/private/test")
    public String test2(){
        log.info("public test");
        return "public test";
    }
    @GetMapping("/anonymous/test")
    public String test3(){
        log.info("public test");
        return "public test";
    }
}
