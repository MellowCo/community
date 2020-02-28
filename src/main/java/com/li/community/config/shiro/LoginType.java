package com.li.community.config.shiro;

/**
 * @Description: 登录类型
 * @Author: li
 * @Create: 2020-02-23 16:36
 */
public enum LoginType {
    PASSWORD("password"), // 密码登录
    NOPASSWD("nopassword"); // 免密登录

    private String code;// 状态值

    private LoginType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
