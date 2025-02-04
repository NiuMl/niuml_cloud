# niuml_cloud

this is tested project

# 配置文件要求

* 1、使用sa token的时候，引入[niuml_common_satoken](niuml_common%2Fniuml_common_satoken)，要指定sa-token.excludeUrls,这个是用来过滤不需求认证的url。
*

# 项目结构以及说明

* 1、niuml_auth：认证模块 <br/>
  ├ &nbsp;引入了公共模块中的[niuml_common_satoken](niuml_common%2Fniuml_common_satoken)
* 2、niuml_common：公共模块
* &nbsp; ├ 2.1 核心包 [niuml_common_core](niuml_common%2Fniuml_common_core)
* &nbsp; ├ 2.2 redis包 [niuml_common_redis](niuml_common%2Fniuml_common_redis) 引入了【[核心包](niuml_common%2Fniuml_common_core)】
* &nbsp; ├ 2.3 satoken包 [niuml_common_satoken](niuml_common%2Fniuml_common_satoken) 引入了【[redis包](niuml_common%2Fniuml_common_redis)】
