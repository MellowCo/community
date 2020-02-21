package com.li.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.community.dto.QuestionDto;

import java.util.List;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-21 15:50
 */
public interface QuestionDtoMapper extends BaseMapper<QuestionDto> {
    List<QuestionDto> list();
}
