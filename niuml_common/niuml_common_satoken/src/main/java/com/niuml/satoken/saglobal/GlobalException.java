package com.niuml.satoken.saglobal;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.niuml.common.core.domain.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 * 因为当前这个模块  凡是要使用web的  都得引入这个，所以全局异常直接写这就行了
 */
@RestControllerAdvice
@Order(1)
@Log4j2
public class GlobalException {

	// 全局异常拦截（拦截项目中的所有异常）
	@ExceptionHandler(Exception.class)
	public R<String> handlerException(Exception e)
			throws Exception {
		log.error("全局异常信息 ex={}", e.getMessage());
		// 打印堆栈，以供调试
		e.printStackTrace();

        return switch (e) {
            case NotLoginException ignored -> R.fail("未登录");
            case NotRoleException nre -> R.fail("角色异常");
            case NotPermissionException npe -> R.fail("权限异常");
            default -> R.fail("系统异常");
        };
	}

	// 拦截参数校验异常
	@ExceptionHandler(BindException.class)
	public R<String> handlerBindException(BindException be){
		String collect = be.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
		return R.fail(collect);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public R<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request)
	{
		String requestURI = request.getRequestURI();
		String value = Objects.requireNonNull(e.getValue()).toString();
		log.error("请求参数类型不匹配'{}',发生系统异常.", requestURI, e);
		String re = String.format("请求参数类型不匹配，参数[%s]要求类型为：'%s'，但输入值为：'%s'", e.getName(), Objects.requireNonNull(e.getRequiredType()).getName(), value);
		return R.fail(re);
	}
}
