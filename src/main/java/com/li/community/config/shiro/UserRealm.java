package com.li.community.config.shiro;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.li.community.entity.User;
import com.li.community.mapper.UserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description: shiro realm
 * @Author: li
 * @Create: 2020-02-22 15:44
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper mapper;

    /*
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /*
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户名
        MyUserToken passwordToken = (MyUserToken) token;
        String userName = passwordToken.getUsername();

        //查询数据库
        User user = mapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getAccountId, userName));
        System.out.println("UserRealm->" + user);

        if (user == null) {
            //用户名不存在
            //shiro底层会抛出UnKnowAccountException
            return null;
        }

        //2、判断密码, 这里的user是principal
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, "", getName());

        return info;
    }
}
