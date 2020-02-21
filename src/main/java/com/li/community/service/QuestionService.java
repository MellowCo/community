package com.li.community.service;

import com.li.community.dto.QuestionDto;
import com.li.community.mapper.QuestionDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-21 16:09
 */
@Service
public class QuestionService {

    @Autowired
    QuestionDtoMapper dtoMapper;

    public List<QuestionDto> list(){
        return dtoMapper.list();
    }
}
