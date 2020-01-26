package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.dto.GithubUser;

/**
 * @author Dong
 * @date 2020/1/26 13:49
 */
public interface AuthorizeService {

    GithubUser getGithubUser(String code,String state);

}
