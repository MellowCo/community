package com.li.community.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.li.community.dto.QuestionDto;
import com.li.community.entity.User;
import com.li.community.mapper.UserMapper;
import com.li.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
                    User user = mapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getToken, value));
                    //User user = mapper.findByToken(value);

                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        //问题列表,分页，第一页，每页2个数据
        return "forward:/questionPage/1/1";
    }

    /**
     * 分页数据
     * @param curPage 当前页
     * @param size    每页大小
     * @param model   返回给前端
     * @Return java.lang.String
     */
    @GetMapping("/questionPage/{curPage}/{size}")
    public String questionPage(@PathVariable(value = "curPage", required = false) Integer curPage, @PathVariable(value = "size", required = false) Integer size, Model model) {
        IPage<QuestionDto> iPage = questionService.questionPage(curPage, size);
        model.addAttribute("page", iPage);

        //生成页码
        long begin = 0;
        long end = 0;
        long total = iPage.getPages();
        long cur = iPage.getCurrent();

        //总页数小于等于5页
        if (total <= 5) {
            begin = 1;
            end = total;
        } else {
            //总页数大于5页
            begin = cur - 2;
            end = cur + 2;
            if (begin <= 0) {
                //起始页小于等于0 改为1~5
                begin = 1;
                end = 5;
            } else if (end > total) {
                //最终页大于总页数
                end = total;
                begin = total - 4;
            }
        }
        model.addAttribute("begin", begin);
        model.addAttribute("end", end);
        return "index";
    }
}
