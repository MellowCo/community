package com.li.community.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-19 14:12
 */
@Data
@TableName("user")
public class User implements Serializable {
    private long id;
    private String accountId;
    private String name;
    private String token;
    //头像
    private String avatarUrl;
    //创建时间戳
    private long gmtCreate;
    //销毁时间戳
    private long gmtModified;

}
