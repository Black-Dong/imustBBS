package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.model.User;

/**
 * @author Dong
 * @date 2020/2/17 14:00
 */
public interface UserService {

    User checkUser(String account, String password);

    ResultDTO<User> checkEmailExist(User user);

    User registerUser(User user);

    User selectUserByUserName(String username);
}
