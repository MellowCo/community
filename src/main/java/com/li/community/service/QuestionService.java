package com.li.community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.community.dto.QuestionDto;
import com.li.community.mapper.QuestionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-21 16:09
 */
@Service
public class QuestionService extends ServiceImpl<QuestionDtoMapper, QuestionDto> implements IQuestionSerivce {

    @Autowired
    QuestionDtoMapper dtoMapper;

    public Page<QuestionDto> questionPage(Integer curPage, Integer size) {
        if (curPage == null || curPage < 1) {
            curPage = 1;
        }

        if (size == null || size > 20) {
            size = 1;
        }

        Page<QuestionDto> page = new Page<>(curPage, size);
        dtoMapper.questionPage(page, new QueryWrapper<>());
        return page;
    }
}
