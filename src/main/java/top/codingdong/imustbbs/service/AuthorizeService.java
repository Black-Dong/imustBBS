package top.codingdong.imustbbs.service;

/**
 * @author Dong
 * @date 2020/1/26 13:49
 */
public interface AuthorizeService<T> {

    /**
     * 授权地址
     *
     * @return 授权地址
     */
    String authorize();

    /**
     * 获取accessToken
     *
     * @param t 至少含有请求编码 code
     * @return accessToken
     */
    String getAccessToken(T t);

    /**
     * 获取用户信息
     *
     * @param accessToken token
     * @return user
     */
    <E> E getUserInfo(String accessToken);

}
