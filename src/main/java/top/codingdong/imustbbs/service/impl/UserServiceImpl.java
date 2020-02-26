package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.entity.User;
import top.codingdong.imustbbs.repository.UserRepository;
import top.codingdong.imustbbs.service.UserService;
import top.codingdong.imustbbs.util.CryptographyUtil;

import java.util.Date;

/**
 * @author Dong
 * @date 2020/2/26 15:37
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void save(User user) {

        userRepository.save(user);
    }

    @Override
    public User findById(Integer id) {
        return userRepository.getOne(id);
    }
}
