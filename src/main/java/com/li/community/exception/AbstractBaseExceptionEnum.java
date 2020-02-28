package com.li.community.exception;

/**
 * @Description: 异常类接口
 * @Author: li
 * @Create: 2020-02-28 15:11
 */
public interface AbstractBaseExceptionEnum {
    /**
     * 获取异常的提示信息
     */
    String getMessage();

    /**
     * 获取异常的状态码
     */
    Integer getCode();
}
