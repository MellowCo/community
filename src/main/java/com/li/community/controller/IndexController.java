package com.li.community.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.li.community.entity.User;
import com.li.community.mapper.UserMapper;
import com.li.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-19 14:53
 */
@Controller
public class IndexController {
    @Autowired
    private UserMapper mapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request, Model model) {
        /*
         * 访问 / 时，先获取本地cookies，
         * 遍历cookies，获取key为token的value，
         * 如果可以通过token，查到用户，说明用户登录过，将token保留在本机中
         * 将查到的user，放到session中
         */
        Cookie[] cookies = request.getCookies();

        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String value = cookie.getValue();
                    User user = mapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getToken,value));
                    //User user = mapper.findByToken(value);

                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        //问题列表
        model.addAttribute("list",questionService.list());
        System.out.println(questionService.list());
        return "index";
    }
}
