package com.li.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.community.dto.QuestionDto;
import com.li.community.mapper.QuestionDtoMapper;
import com.li.community.service.IQuestionDtoSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-21 16:09
 */
@Service
public class QuestionDtoService extends ServiceImpl<QuestionDtoMapper, QuestionDto> implements IQuestionDtoSerivce {

    @Autowired
    QuestionDtoMapper dtoMapper;

    /**
     * 分页查询
     * @param curPage 当前页
     * @param size    每页的数据个数
     * @Return com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.li.community.dto.QuestionDto>
     */
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


    /**
     * 根据问题文章的id 获取整片文章
     * @param id 问题文章的id号
     * @Return com.li.community.dto.QuestionDto
     */
    public QuestionDto questionById(Long id) {
        QueryWrapper<QuestionDto> wrapper = new QueryWrapper<>();
        wrapper.eq("q.id", id);
        return dtoMapper.questionById(wrapper);
    }


}
