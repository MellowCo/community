package com.li.community.exception;

/**
 * @Description: 异常类
 * @Author: li
 * @Create: 2020-02-28 15:19
 */
public class CustomizeException extends RuntimeException{

    private String message;
    private Integer code;

    public CustomizeException(AbstractBaseExceptionEnum errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
