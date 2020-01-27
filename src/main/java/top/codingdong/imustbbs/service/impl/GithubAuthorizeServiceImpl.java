package top.codingdong.imustbbs.service.impl;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.dto.GithubOAuthDto;
import top.codingdong.imustbbs.dto.GithubUser;
import top.codingdong.imustbbs.service.AuthorizeService;

import java.io.IOException;

/**
 * @author Dong
 * @date 2020/1/26 13:52
 */
@Service
@Transactional
public class GithubAuthorizeServiceImpl implements AuthorizeService<GithubOAuthDto> {


    @Value("${github.client_id}")
    private String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;
    @Value("${github.redirect_uri}")
    private String redirectUri;

    public GithubUser getGithubUser(String code,String state) {
        GithubOAuthDto githubOAuthDto = new GithubOAuthDto();
        githubOAuthDto.setClient_id(clientId);
        githubOAuthDto.setClient_secret(clientSecret);
        githubOAuthDto.setRedirect_uri(redirectUri);
        githubOAuthDto.setCode(code);
        githubOAuthDto.setState(state);
        String accessToken = getAccessToken(githubOAuthDto);
        GithubUser userInfo = getUserInfo(accessToken);
        return userInfo;
    }

    /**
     * 返回申请授权管理链接
     *  访问后会重定向到redirectURI
     * @return
     */
    @Override
    public String authorize() {
        return "https://github.com/login/oauth/authorize?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&scope=user&state=1";
    }


    /**
     * 获取AccessToken，需要post访问，所以使用了okhttp
     * @param githubOAuthDto（内涵code等信息）
     * @return
     */
    @Override
    public String getAccessToken(GithubOAuthDto githubOAuthDto) {

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(githubOAuthDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.err.println(string);
            return string;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取UserInfo，需要Get请求，仍然使用okhttp
     * @param accessToken token
     * @return
     */
    @Override
    public GithubUser getUserInfo(String accessToken) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?" + accessToken)
                .build();
        try(Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
//            System.out.println(githubUser);
            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
