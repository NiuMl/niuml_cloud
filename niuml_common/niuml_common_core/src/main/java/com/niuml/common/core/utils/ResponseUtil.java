package com.niuml.common.core.utils;

import com.niuml.common.core.domain.R;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/***
 * @author niumengliang
 * Date:2024/12/10
 * Time:15:00
 * 通过使用HttpServletResponse对象，将数据返回给客户端
 * 为什么要用这个，是因为有些操作还没有到springmvc里面，有些异常拦截是拦截不到的，使用的是过滤器
 */
public class ResponseUtil {

    private static final String CONTENT_TYPE = "application/json;charset=utf-8";
    private static final String CHARACTER_ENCODING = "UTF-8";

    public static void send(HttpServletResponse response, String msg) {
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CHARACTER_ENCODING);
        try {
            response.getWriter().write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void send(HttpServletResponse response, R result) {
        response.setContentType(CONTENT_TYPE);
        response.setCharacterEncoding(CHARACTER_ENCODING);
        try {
            response.getWriter().write(JsonUtil.toJson(result));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
