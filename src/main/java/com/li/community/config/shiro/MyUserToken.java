package com.li.community.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @Description: 继承UsernamePasswordToken 实现第三方登录
 * @Author: li
 * @Create: 2020-02-23 16:37
 */
public class MyUserToken extends UsernamePasswordToken {

    private LoginType type;

    public MyUserToken(){
        super();
    }

    public MyUserToken(String username, String password, LoginType type, boolean rememberMe,  String host) {
        super(username, password, rememberMe,  host);
        this.type = type;
    }
    /**免密登录*/
    public MyUserToken(String username,boolean rememberMe) {
        super(username, "", rememberMe, null);
        this.type = LoginType.NOPASSWD;
    }

    /**账号密码登录*/
    public MyUserToken(String username, String password) {
        super(username, password, false, null);
        this.type = LoginType.PASSWORD;
    }

    public LoginType getType() {
        return type;
    }


    public void setType(LoginType type) {
        this.type = type;
    }

}
