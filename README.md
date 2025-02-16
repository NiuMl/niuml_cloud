# niuml_cloud

this is tested project

# 配置文件要求

* 1、使用sa token的时候，引入[niuml_common_satoken](niuml_common%2Fniuml_common_satoken)，要指定sa-token.excludeUrls,这个是用来过滤不需求认证的url。
*

# 项目结构以及说明

* niuml_auth：认证模块 <br/>
  ├ &nbsp;引入了公共模块中的[niuml_common_satoken](niuml_common%2Fniuml_common_satoken)
* niuml_auth_security:认证模块(基于security) <br/>
  ├ &nbsp;引入了公共模块中的[niuml_common_security](niuml_common%2Fniuml_common_security)
* niuml_common：公共模块
* &nbsp; ├  核心包 [niuml_common_core](niuml_common%2Fniuml_common_core)
* &nbsp; ├  redis包 [niuml_common_redis](niuml_common%2Fniuml_common_redis) 引入了【[核心包](niuml_common%2Fniuml_common_core)】
* &nbsp; ├  satoken包 [niuml_common_satoken](niuml_common%2Fniuml_common_satoken) 引入了【[redis包](niuml_common%2Fniuml_common_redis)】，使用了sa-token来进行登录鉴权
* &nbsp; ├  security包[niuml_common_security](niuml_common%2Fniuml_common_security),引入了【[核心包](niuml_common%2Fniuml_common_core)】,使用spring security来进行登录鉴权,引入模块如果需要配置某个接口不需要鉴权，则配置SecurityFilterChain即可。
