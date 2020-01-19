package com.li.community.controller;

import com.li.community.dto.AaccessTokenDto;
import com.li.community.entity.User;
import com.li.community.mapper.UserMapper;
import com.li.community.provider.GithubProvider;
import com.li.community.dto.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String callback(AaccessTokenDto token, HttpServletRequest request){
        token.setClient_id(client_id);
        token.setClient_secret(client_secret);

        String accessToken = githubProvider.getAccessToken(token);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        if (githubUser != null){
            //登录成功
            request.getSession().setAttribute("user",githubUser);
            User user = new User();
            user.setName(githubUser.getName());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            //随机生成token
            user.setToken(UUID.randomUUID().toString());
            //获取当前的时间戳
            user.setGmt_create(System.currentTimeMillis());
            mapper.insertUser(user);
            System.out.println(user);

            return "redirect:/";
        }else{
            //登陆失败
            return "redirect:/";
        }
    }
}
