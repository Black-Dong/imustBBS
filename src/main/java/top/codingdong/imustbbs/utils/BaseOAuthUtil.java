package top.codingdong.imustbbs.utils;

import top.codingdong.imustbbs.dto.GithubOAuthDto;
import top.codingdong.imustbbs.dto.GithubUser;

/**
 * @author Dong
 * @date 2020/1/24 9:37
 */
public interface BaseOAuthUtil<T> {

    /**
     * 授权地址
     *
     * @return 授权地址
     */
    public String authorize();

    /**
     * 获取accessToken
     *
     * @param t 至少含有请求编码 code
     * @return accessToken
     */
    public String getAccessToken(T t);

    /**
     * 获取用户信息
     *
     * @param accessToken token
     * @return user
     */
    public <E> E getUserInfo(String accessToken);

}
