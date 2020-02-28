package com.li.community.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.li.community.dto.QuestionDto;
import com.li.community.entity.User;
import com.li.community.mapper.UserMapper;
import com.li.community.service.impl.QuestionDtoService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    private QuestionDtoService questionService;


    @GetMapping("/")
    public String index(HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        System.out.println("IndexController中");

        if (subject.isRemembered() || subject.isAuthenticated()) {
            User user = (User)subject.getPrincipal();
            System.out.println("IndexController->"+ user);

            request.getSession().setAttribute("user", user);
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
