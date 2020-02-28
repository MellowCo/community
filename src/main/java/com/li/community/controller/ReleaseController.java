package com.li.community.controller;

import com.li.community.entity.Question;
import com.li.community.entity.User;
import com.li.community.service.impl.QuestionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private QuestionService service;

    @GetMapping("/release")
    public String publish() {
        return "release";
    }

    @PostMapping("/release")
    public String doPulish(Question question, HttpServletRequest request, Model model) {

        Subject subject = SecurityUtils.getSubject();

        User user = (User) subject.getPrincipal();


        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());

        //添加或者修改
        System.out.println("ReleaseController 添加或修改-》");
        service.saveOrUpdate(question);

        return "redirect:/";
    }

    /**
     * @param id 编辑的内容的文章id
     * @Return java.lang.String
     */
    @GetMapping("/release/{id}")
    public String updateQuestion(@PathVariable(name = "id") Long id, Model model) {

        Question question = service.getById(id);
        System.out.println("updateQuestion->" + question);
        model.addAttribute("question", question);

        //跳转到发布页面
        return "release";
    }


}
