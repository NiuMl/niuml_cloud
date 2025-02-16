package com.niuml.common.core.utils;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/***
 * @author niumengliang
 * Date:2024/12/12
 * Time:11:06
 */
public class RequestUtil {

    public static Map<String,Object> req2Map(HttpServletRequest request) throws IOException {
        // 提取请求数据
        String requestJsonData = request.getReader().lines()
                .collect(Collectors.joining(System.lineSeparator()));
        return JsonUtil.parseToMap(requestJsonData);
    }
}
