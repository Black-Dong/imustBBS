package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.po.User;

import java.util.List;

/**
 * 用户业务接口
 *
 * @author Dong
 * @date 2020/2/26 15:36
 */
public interface UserService {

    User findByUserName(String userName);

    User findByEmail(String email);

    void save(User user);

    User findById(Integer id);

    List<User> selectAllmember(Integer pageNumber, Integer pageSize);

    void disableUser(Integer id);

    void unDisableUser(Integer userId);

    List<User> selectAdmins(Integer pageNumber, Integer pageSize);

    /**
     * 升级用户权限为管理员
     *
     * @param userId
     */
    void authorizeAdmin(Integer userId);

    /**
     * 管理员用户取消授权为普通用户
     *
     * @param userId
     */
    void deauthorizeAdmin(Integer userId);
}
