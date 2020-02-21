package com.li.community.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-12 17:17
 */
@Data
public class GithubUser {
    private String name;
    private long id;
    private String bio;
    private String avatarUrl;
}
