package com.li.community.controller;

import com.li.community.entity.GithubAaccessToken;
import com.li.community.provider.GithubProvider;
import com.li.community.entity.GithubUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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


    @GetMapping("/callback")
    public String callback(GithubAaccessToken token){
        token.setClient_id(client_id);
        token.setClient_secret(client_secret);

        String accessToken = githubProvider.getAccessToken(token);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user);
        System.out.println(accessToken);
        return "index";
    }
}
