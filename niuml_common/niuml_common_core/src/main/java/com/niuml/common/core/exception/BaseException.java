package com.niuml.common.core.exception;


/***
 * @author niumengliang
 * Date:2024/12/11
 * Time:15:51
 */
public class BaseException extends RuntimeException{

    private int code;
    private String message;
    public BaseException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
