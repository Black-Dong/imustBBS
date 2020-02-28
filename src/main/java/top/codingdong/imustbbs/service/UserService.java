package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.po.User;

/**
 * @author Dong
 * @date 2020/2/26 15:36
 */
public interface UserService {

    User findByUserName(String userName);

    User findByEmail(String email);

    void save(User user);

    User findById(Integer id);
}
