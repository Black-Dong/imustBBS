package top.codingdong.imustbbs.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.DTO.RePasswordDTO;
import top.codingdong.imustbbs.po.User;
import top.codingdong.imustbbs.mapper.UserMapper;
import top.codingdong.imustbbs.service.UserService;
import top.codingdong.imustbbs.util.CryptographyUtil;

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
    public User selectByUsername(String userName) {
        return usermapper.selectUserByUsername(userName);
    }

    @Override
    public User selectByEmail(String email) {
        return usermapper.selectUserByEmail(email);
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
    public User selectById(Integer id) {
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
                .andEqualTo("roleName", "管理员");
        PageHelper.startPage(pageNumber, pageSize);
        return usermapper.selectByExample(example);
    }

    @Override
    public void authorizeAdmin(Integer userId) {

        usermapper.modifyRoleNameToAdmin(userId);
    }

    @Override
    public void deauthorizeAdmin(Integer userId) {
        usermapper.modifyRoleNameToMember(userId);
    }


    /**
     * 根据传入的用户名，模糊查询用户列表，只查询5个
     *
     * @param searchName
     * @return
     */
    @Override
    public List<User> listLikeUsername(String searchName) {


        PageHelper.startPage(1, 5);
        List<User> usersLikeUsername = usermapper.listLikeUsername(searchName);

        return usersLikeUsername;
    }

    @Override
    public User modifyBasicInformation(User user) {
        usermapper.updateByPrimaryKeySelective(user);
        return usermapper.selectByPrimaryKey(user.getUserId());
    }

    @Override
    public int rePassword(RePasswordDTO repasswordDTO) {
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("userId", repasswordDTO.getUserId())
                .andEqualTo("password", CryptographyUtil.md5(repasswordDTO.getNowPassword()));
        User dbUser = usermapper.selectOneByExample(example);
        if (dbUser != null) {
            usermapper.modifyPasswordById(dbUser.getUserId(), CryptographyUtil.md5(repasswordDTO.getNewPassword()));
            return 1;
        }
        return 0;
    }
}
