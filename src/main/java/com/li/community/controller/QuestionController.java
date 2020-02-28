package com.li.community.controller;

import com.li.community.dto.QuestionDto;
import com.li.community.exception.CustomizeException;
import com.li.community.exception.ExceptionEnum;
import com.li.community.service.impl.QuestionDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-02-22 16:40
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionDtoService questionService;

    /**
     * 根据问题文章的id 获取整篇文章
     * @param id
     * @param model
     * @Return java.lang.String
     */
    @GetMapping("/question/{id}")
    public String getQuestion(@PathVariable(name="id") Long id, Model model) {
        QuestionDto questionDto = questionService.questionById(id);

        //未找到文章 抛出异常
        if (questionDto == null) {
            throw new CustomizeException(ExceptionEnum.QUESTION_NOT_FOUND);
        }

        model.addAttribute("questionDto", questionDto);
        return "question";
    }
}
