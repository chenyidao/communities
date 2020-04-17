package com.community.cyd.provider;

import com.alibaba.fastjson.JSON;
import com.community.cyd.dto.AccessTokenDTO;
import com.community.cyd.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {

    //获取access_token
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            //string格式为：access_token=3d8c927226052fdab7166d231026538ae8604b95&scope=user&token_type=bearer
            //而我们要获取的是前面的token值
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //通过accessToken获取用户信息
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
