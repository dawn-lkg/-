package com.dawn.dawn.common.system.service.impl;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.dawn.dawn.common.core.constant.GitHubConstants;
import com.dawn.dawn.common.system.entity.GitHubUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenliming
 * @date 2024/6/16 下午8:34
 */

@Service
public class GitHubOAuthService {
    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    private  String CLIENT_ID ;
    @Value("${spring.security.oauth2.client.registration.github.client-secret}")
    private  String CLIENT_SECRET;

    public JSONObject getGitHubAccessToken(String code) {
        Map<String, Object> data = new HashMap<>();
        data.put("client_id", CLIENT_ID);
        data.put("client_secret", CLIENT_SECRET);
        data.put("code", code);

        String post = HttpRequest.post(GitHubConstants.TOKEN_URL)
                .header(Header.ACCEPT, "application/json")
                .body(JSONUtil.toJsonStr(data))
                .execute().body();

        return JSONObject.parseObject(post);
    }

    public GitHubUser getGitHubUser(String accessToken) {
        String user = HttpRequest.get(GitHubConstants.USER_URL)
                .header("Authorization", "token " + accessToken)
                .execute().body();
        System.out.println(JSONObject.parseObject(user));
        return JSONObject.parseObject(user, GitHubUser.class);
    }
}
