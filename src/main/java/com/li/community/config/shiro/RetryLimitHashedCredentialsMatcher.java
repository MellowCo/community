package com.li.community.config.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

/**
 * @Description: 免密登录
 * @Author: li
 * @Create: 2020-02-23 16:39
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {




    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        MyUserToken token1 = (MyUserToken) token;
        //如果是免密登录直接返回true
        if (token1.getType().equals(LoginType.NOPASSWD)){
            return true;
        }
        //不是免密登录，调用父类的方法
        return super.doCredentialsMatch(token, info);
    }
}
