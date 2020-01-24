package top.codingdong.imustbbs.utils;

import top.codingdong.imustbbs.dto.GithubOAuthDto;
import top.codingdong.imustbbs.dto.GithubUser;

/**
 * @author Dong
 * @date 2020/1/24 9:37
 */
public interface BaseOAuthUtil {

    /**
     * 授权地址
     *
     * @return 授权地址
     */
    public String authorize();

    /**
     * 获取accessToken
     *
     * @param code 请求编码
     * @return accessToken
     */
    public String getAccessToken(GithubOAuthDto githubOAuthDto);

    /**
     * 获取用户信息
     *
     * @param accessToken token
     * @return user
     */
    public <T> T getUserInfo(String accessToken);

}
