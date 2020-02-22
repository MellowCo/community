package com.li.community.config;

import com.li.community.entity.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: shiro的相关配置
 * @Author: li
 * @Create: 2020-02-22 15:43
 */
@Configuration
public class ShiroConfig {

    /*
     *
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联 userRealm
        securityManager.setRealm(userRealm);
        //记住我
        securityManager.setRememberMeManager(rememberMeManager());
        
        return securityManager;
    }

    @Bean
    public UserRealm userRealm() {
        UserRealm realm = new UserRealm();
        //设置加密
        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /*
     * 权限
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        //设置安全管理器
        factoryBean.setSecurityManager(securityManager);

        //设置权限
        Map<String, String> map = new HashMap<>();

        // / 不需要权限
        map.put("/", "anon");
        //静态资源不需要权限
        map.put("/js/**", "anon");
        map.put("/css/**", "anon");
        map.put("/fonts/**", "anon");

        map.put("/questionPage/**", "anon");
        map.put("/logout", "logout");
        map.put("/**", "authc");
        //设置权限过滤器
        factoryBean.setFilterChainDefinitionMap(map);

        //设置未登录的跳转地址
        factoryBean.setLoginUrl("/");
        return factoryBean;
    }

    /*
     * md5加密
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashIterations(1024);
        matcher.setHashAlgorithmName("MD5");
        return matcher;
    }

    /**
     * cookie对象;
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        //System.out.println("ShiroConfiguration.rememberMeCookie()");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(259200);
        return simpleCookie;
    }

    /**
     * cookie管理对象;
     */
    @Bean
    public CookieRememberMeManager rememberMeManager() {
        //System.out.println("ShiroConfiguration.rememberMeManager()");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        return cookieRememberMeManager;
    }
}


