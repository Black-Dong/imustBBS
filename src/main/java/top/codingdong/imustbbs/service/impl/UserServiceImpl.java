package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.po.User;
import top.codingdong.imustbbs.mapper.UserMapper;
import top.codingdong.imustbbs.service.UserService;

/**
 * @author Dong
 * @date 2020/2/26 15:37
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper usermapper;

    @Override
    public User findByUserName(String userName) {
        return usermapper.findUserByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return usermapper.findUserByEmail(email);
    }

    @Override
    public void save(User user) {

        if (user.getUserId() != null){
            usermapper.updateByPrimaryKeySelective(user);
        }else {
            usermapper.insertSelective(user);
        }
    }

    @Override
    public User findById(Integer id) {
        return usermapper.selectByPrimaryKey(id);
    }
}
