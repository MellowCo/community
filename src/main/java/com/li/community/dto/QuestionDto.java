package com.li.community.dto;

import com.li.community.entity.Question;
import com.li.community.entity.User;
import lombok.Data;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-21 15:48
 */
@Data
public class QuestionDto {
    private Question question;
    private User user;

}
