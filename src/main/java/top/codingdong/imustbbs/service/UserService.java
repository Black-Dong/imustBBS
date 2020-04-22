package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.DTO.RePasswordDTO;
import top.codingdong.imustbbs.po.User;

import java.util.List;

/**
 * 用户业务接口
 *
 * @author Dong
 * @date 2020/2/26 15:36
 */
public interface UserService {

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    User selectByUsername(String userName);

    /**
     * 通过邮箱查找用户
     *
     * @param email
     * @return
     */
    User selectByEmail(String email);

    /**
     * 根据传入的user的id判断是 新增用户还是修改用户
     *
     * @param user
     */
    void save(User user);

    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    User selectById(Integer id);

    /**
     * 查询普通用户
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<User> selectAllmember(Integer pageNumber, Integer pageSize);

    /**
     * 禁用用户
     *
     * @param id
     */
    void disableUser(Integer id);

    /**
     * 解禁用户
     *
     * @param userId
     */
    void unDisableUser(Integer userId);

    /**
     * 查询所有管理员
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
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

    /**
     * 根据传入的用户名，模糊查询用户列表
     *
     * @param searchName
     * @return
     */
    List<User> listLikeUsername(String searchName);

    /**
     * 修改用户基本信息，返回修改后的信息
     *
     * @param user
     * @return
     */
    User modifyBasicInformation(User user);

    int rePassword(RePasswordDTO repasswordDTO);
}
