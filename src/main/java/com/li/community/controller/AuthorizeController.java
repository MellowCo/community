package com.li.community.controller;

import com.li.community.config.shiro.MyUserToken;
import com.li.community.dto.AaccessTokenDto;
import com.li.community.dto.GithubUser;
import com.li.community.entity.User;
import com.li.community.mapper.UserMapper;
import com.li.community.provider.GithubProvider;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-12 16:30
 */

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Autowired
    private UserMapper mapper;


    @GetMapping("/callback")
    public String callback(AaccessTokenDto aaccessTokenDto, HttpServletResponse response, HttpServletRequest request) {
        aaccessTokenDto.setClient_id(client_id);
        aaccessTokenDto.setClient_secret(client_secret);

        String accessToken = githubProvider.getAccessToken(aaccessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (githubUser != null) {
            //登录成功
            User user = new User();
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            //随机生成token
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            //获取当前的时间戳
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            //添加头像地址
            user.setAvatarUrl(githubUser.getAvatarUrl());

            Subject subject = SecurityUtils.getSubject();

            MyUserToken myUserToken = new MyUserToken(user.getAccountId(), true);
            //3 执行登录
            try {
                System.out.println("AuthorizeController 登录成功");
                subject.login(myUserToken);
                //登录成功
            } catch (UnknownAccountException e) {
                //UnknownAccountException异常
                //表示登录失败，用户名不存在
                System.out.println("AuthorizeController 用户名不存在");
                mapper.insert(user);
                //添加到数据库，重新登录
                myUserToken = new MyUserToken(user.getAccountId(), true);
                subject.login(myUserToken);

                System.out.println("callback->" + user);
            } finally {
                return "redirect:/";
            }

        } else {
            //登陆失败
            System.out.println("AuthorizeController 登录失败");
            return "redirect:/";
        }
    }

}
