package com.li.community.exception;

public enum ExceptionEnum implements AbstractBaseExceptionEnum{

    QUESTION_NOT_FOUND(2000,"问题不存在，请重新查找");

    private String message;
    private Integer code;

    public void setCode(Integer code) {
        this.code = code;
    }

    ExceptionEnum(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
