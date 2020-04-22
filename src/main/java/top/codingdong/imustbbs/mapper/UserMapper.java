package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import top.codingdong.imustbbs.po.User;

import java.util.List;

/**
 * 用户Repository接口
 *
 * @author Dong
 * @date 2020/2/26 15:30
 */

public interface UserMapper extends Mapper<User> {

    @Select("select * from t_user where username = #{username}")
    User selectUserByUsername(String username);

    @Select("select * from t_user where email = #{email}")
    User selectUserByEmail(String email);

    @Update("update t_user set is_off = 1 where user_id = #{id}")
    void disableUserById(Integer id);

    @Update("update t_user set is_off = 0 where user_id = #{id}")
    void unDisableUserById(Integer id);

    @Update("update t_user set role_name = '管理员' where user_id = #{userId}")
    void modifyRoleNameToAdmin(Integer userId);

    @Update("update t_user set role_name = '会员' where user_id = #{userId}")
    void modifyRoleNameToMember(Integer userId);

    @Select("select * from t_user where username like concat('%',#{searchName},'%')")
    List<User> listLikeUsername(String searchName);

    @Update("update t_user set password = #{newPassword} where user_id = #{userId}")
    void modifyPasswordById(Integer userId, String newPassword);
}
