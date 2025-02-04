package com.niuml.common.redis.imports;

import com.niuml.common.redis.configure.RedisConfig;
import com.niuml.common.redis.service.RedisService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/***
 * @author niumengliang
 * Date:2025/1/18
 * Time:09:50
 * 使用下面这种方式  不然就得在resources里面创建 META-INF/spring目录
 * 里面创建一个org.springframework.boot.autoconfigure.AutoConfiguration.imports文件
 * 里面写入
 *        com.niuml.common.redis.configure.RedisConfig
 *        com.niuml.common.redis.service.RedisService
 */
@Configuration
@Import({RedisConfig.class, RedisService.class})
public class CommonRedisImportConfig {
}
