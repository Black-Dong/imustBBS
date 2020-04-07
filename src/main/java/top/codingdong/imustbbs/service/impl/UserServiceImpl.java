package top.codingdong.imustbbs.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.po.User;
import top.codingdong.imustbbs.mapper.UserMapper;
import top.codingdong.imustbbs.service.UserService;

import java.util.List;

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
        return usermapper.findUserByUsername(userName);
    }

    @Override
    public User findByEmail(String email) {
        return usermapper.findUserByEmail(email);
    }

    @Override
    public void save(User user) {

        if (user.getUserId() != null) {
            usermapper.updateByPrimaryKeySelective(user);
        } else {
            usermapper.insertSelective(user);
        }
    }

    @Override
    public User findById(Integer id) {
        return usermapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> selectAllmember(Integer pageNumber, Integer pageSize) {
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("roleName", "会员");
        PageHelper.startPage(pageNumber, pageSize);
        return usermapper.selectByExample(example);
    }

    @Override
    public void disableUser(Integer id) {
        usermapper.disableUserById(id);
    }

    @Override
    public void unDisableUser(Integer userId) {
        usermapper.unDisableUserById(userId);
    }

    @Override
    public List<User> selectAdmins(Integer pageNumber, Integer pageSize) {
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("roleName","管理员");
        PageHelper.startPage(pageNumber,pageSize);
        return usermapper.selectByExample(example);
    }

    @Override
    public void authorizeAdmin(Integer userId) {

        usermapper.modifyRoleNameToAdmin(userId);
    }
}
