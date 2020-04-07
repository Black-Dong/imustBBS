package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import top.codingdong.imustbbs.po.User;

/**
 * 用户Repository接口
 *
 * @author Dong
 * @date 2020/2/26 15:30
 */

public interface UserMapper extends Mapper<User> {

    @Select("select * from t_user where username = #{username}")
    User findUserByUsername(String username);

    @Select("select * from t_user where email = #{email}")
    User findUserByEmail(String email);

    @Update("update t_user set is_off = 1 where user_id = #{id}")
    void disableUserById(Integer id);

    @Update("update t_user set is_off = 0 where user_id = #{id}")
    void unDisableUserById(Integer id);

    @Update("update t_user set role_name = '管理员' where user_id = #{userId}")
    void modifyRoleNameToAdmin(Integer userId);
}
