package com.li.community.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-19 14:12
 */
@Data
@TableName("user")
public class User {
    private long id;
    private String account_id;
    private String name;
    private String token;
    //头像
    private String avatarUrl;
    //创建时间戳
    private long gmt_create;
    //销毁时间戳
    private long gmt_modified;

}
