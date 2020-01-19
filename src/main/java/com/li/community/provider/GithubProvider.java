package com.li.community.provider;

import com.alibaba.fastjson.JSON;
import com.li.community.dto.AaccessTokenDto;
import com.li.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description:
 * @Author: li
 * @Create: 2020-01-12 16:49
 */
@Component
public class GithubProvider {

    public String getAccessToken(AaccessTokenDto token) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON.toJSONString(token), mediaType);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();

        try {
            Response response = client.newCall(request).execute();
            //access_token=317c327b593214e115ae074dd2100ccc07012715&scope=&token_type=bearer
            String responseTxt = response.body().string();
            //对响应的数据进行分割，提取access_token
            String access_token = responseTxt.split("&")[0].split("=")[1];
            return access_token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUser(String token) {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + token)
                .build();

        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseTxt = response.body().string();
            GithubUser user = (GithubUser) JSON.parseObject(responseTxt, GithubUser.class);
            return user;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}

