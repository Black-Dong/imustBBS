package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.mapper.UserMapper;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.UserService;
import top.codingdong.imustbbs.util.MD5Utils;

/**
 * @author Dong
 * @date 2020/2/17 14:01
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User checkUser(String account, String password) {
        password = MD5Utils.code(password);

        Example userExample = new Example(User.class);
        userExample.createCriteria()
                .andEqualTo("username",account)
                .andEqualTo("password",password);
        userExample.or()
                .andEqualTo("email",account)
                .andEqualTo("password",password);
        User user = userMapper.selectOneByExample(userExample);
        return user;
    }
}
