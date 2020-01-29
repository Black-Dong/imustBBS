package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.model.User;

/**
 * @author Dong
 * @date 2020/1/26 13:59
 */
public interface UserService {

    User createUser(User user);

    User findByToken(String token);
}
