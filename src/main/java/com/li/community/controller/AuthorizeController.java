package com.li.community.controller;

import com.li.community.dto.AaccessTokenDto;
import com.li.community.dto.GithubUser;
import com.li.community.entity.User;
import com.li.community.mapper.UserMapper;
import com.li.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
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
    private String client_id ;

    @Value("${github.client.secret}")
    private String client_secret ;

    @Autowired
    private UserMapper mapper;


    @GetMapping("/callback")
    public String callback(AaccessTokenDto aaccessTokenDto, HttpServletResponse response){
        aaccessTokenDto.setClient_id(client_id);
        aaccessTokenDto.setClient_secret(client_secret);

        String accessToken = githubProvider.getAccessToken(aaccessTokenDto);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (githubUser != null){
            //登录成功
            User user = new User();
            user.setName(githubUser.getName());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            //随机生成token
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            //获取当前的时间戳
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());

            //添加头像地址
            user.setAvatarUrl(githubUser.getAvatarUrl());

            mapper.insert(user);
            //将token放入到cookie中，保留在本地计算机上
            response.addCookie(new Cookie("token",token));

            return "redirect:/";
        }else{
            //登陆失败
            return "redirect:/";
        }
    }
}
