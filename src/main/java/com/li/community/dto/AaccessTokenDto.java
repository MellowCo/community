package com.li.community.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-12 16:52
 */
@Data
public class AaccessTokenDto {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
