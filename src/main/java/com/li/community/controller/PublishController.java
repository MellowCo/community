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
 * @Description:
 * @Author: li
 * @Create: 2020-01-19 16:07
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper mapper;

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPulish(Question question, HttpServletRequest request, Model model){


        User user = (User) request.getSession().getAttribute("user");
        if (user == null){
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        mapper.insert(question);
        //mapper.insertQuestion(question);

        return "redirect:/";

    }
}
