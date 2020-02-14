package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.mapper.UserMapper;
import top.codingdong.imustbbs.mapper.UserMapperExt;
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
    private UserMapperExt userMapperExt;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User createUser(User user) {
        User newUser = userMapperExt.getUserByAccountAndSource(user.getAccountId(),user.getSource());
        if (newUser != null){
            newUser.setUpdateTime(user.getUpdateTime());
            newUser.setName(user.getName());
            newUser.setAvatarUrl(user.getAvatarUrl());
            newUser.setToken(user.getToken());
            userMapperExt.updateUserSource(newUser);
            return newUser;
        }else {
            user.setCreateTime(System.currentTimeMillis());
            user.setUpdateTime(System.currentTimeMillis());
            userMapper.insert(user);
            return user;
        }
    }

    @Override
    public User findByToken(String token) {
        return userMapperExt.findByToken(token);
    }
}
