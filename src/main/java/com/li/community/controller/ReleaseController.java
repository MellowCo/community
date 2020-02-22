package com.li.community.controller;

import com.li.community.entity.Question;
import com.li.community.entity.User;
import com.li.community.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 发布问题的controller
 * @Author: li
 * @Create: 2020-01-19 16:07
 */
@Controller
public class ReleaseController {

    @Autowired
    private QuestionMapper mapper;

    @GetMapping("/release")
    public String publish(){
        return "release";
    }

    @PostMapping("/release")
    public String doPulish(Question question, HttpServletRequest request, Model model){


        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error", "用户未登录");
            return "release";
        }

        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        mapper.insert(question);
        //mapper.insertQuestion(question);

        return "redirect:/";

    }
}
