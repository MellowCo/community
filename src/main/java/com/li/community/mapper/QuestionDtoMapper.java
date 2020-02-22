package com.li.community.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.li.community.dto.QuestionDto;
import org.apache.ibatis.annotations.Param;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-21 15:50
 */
public interface QuestionDtoMapper extends BaseMapper<QuestionDto> {
    IPage<QuestionDto> questionPage(Page<QuestionDto> page, @Param(Constants.WRAPPER) Wrapper<QuestionDto> queryWrapper);
}
