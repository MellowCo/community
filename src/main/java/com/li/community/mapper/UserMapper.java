package com.li.community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.li.community.entity.User;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-14 15:29
 */

public interface UserMapper extends BaseMapper<User> {

    //User findByToken(@Param("token") String value);
}
