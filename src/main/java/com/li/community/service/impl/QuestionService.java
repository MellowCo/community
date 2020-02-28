package com.li.community.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.community.entity.Question;
import com.li.community.mapper.QuestionMapper;
import com.li.community.service.IQuestionService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-02-28 13:41
 */
@Service
public class QuestionService extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
}
