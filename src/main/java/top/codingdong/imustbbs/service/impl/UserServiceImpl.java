package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.mapper.UserMapper;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.UserService;

/**
 * @author Dong
 * @date 2020/1/26 13:59
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void insertUser(User user) {
        User newUser = userMapper.getUserByAccountAndSource(user.getAccountId(),user.getSource());
        if (newUser != null){
            newUser.setUpdateTime(user.getUpdateTime());
            newUser.setName(user.getName());
            newUser.setToken(user.getToken());
            userMapper.updateUserSource(newUser.getToken(),newUser.getUpdateTime(),newUser.getId());
        }else {
            userMapper.insertUser(user);
        }
    }

    @Override
    public User findByToken(String token) {
        return userMapper.findByToken(token);
    }
}
