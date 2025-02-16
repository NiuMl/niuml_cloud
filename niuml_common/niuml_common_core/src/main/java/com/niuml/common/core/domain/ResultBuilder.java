package com.niuml.common.core.domain;


import com.niuml.common.core.utils.I18nMessageTool;

public final class ResultBuilder {

  private final R result;

  private ResultBuilder() {
    result = new R();
  }

  public static ResultBuilder initResult() {
    return new ResultBuilder();
  }

  public ResultBuilder code(int code) {
    result.setCode(code);
    return this;
  }

  public ResultBuilder msg(String msg) {
    // 使用Builder方式构建Result对象，翻译响应数据
    result.setMsg(I18nMessageTool.translate(msg));
    return this;
  }

  public <T> ResultBuilder data(T data) {
    result.setData(data);
    return this;
  }

  public R build() {
    return result;
  }
}
