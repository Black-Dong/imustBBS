package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.enums.StatusEnum;
import top.codingdong.imustbbs.enums.UserTypeEnum;
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

    @Override
    public ResultDTO<User> checkEmailExist(User user) {
        Example userExample = new Example(User.class);
        userExample.createCriteria()
                .andEqualTo("email", user.getEmail());
        User exitUser = userMapper.selectOneByExample(userExample);
        if (exitUser != null){
            return ResultDTO.warnOf(StatusEnum.EMAIL_EXIST);
        }
        return ResultDTO.success();
    }

    @Override
    public User registerUser(User user) {
        user.setPassword(MD5Utils.code(user.getPassword()));
        user.setCreateTime(System.currentTimeMillis());
        user.setUpdateTime(System.currentTimeMillis());
        user.setType(UserTypeEnum.VIEWER.getType());
        userMapper.insertSelective(user);
        return selectUserByEmail(user.getEmail());
    }

    @Override
    public User selectUserByUserName(String username) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username",username);
        return userMapper.selectOneByExample(example);
    }

    @Override
    public User selectUserByEmail(String email) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("email",email);
        return userMapper.selectOneByExample(example);
    }
}
