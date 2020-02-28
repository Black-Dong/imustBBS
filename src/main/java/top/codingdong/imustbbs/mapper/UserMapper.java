package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import top.codingdong.imustbbs.po.User;

/**
 * 用户Repository接口
 *
 * @author Dong
 * @date 2020/2/26 15:30
 */

public interface UserMapper extends Mapper<User> {

    @Select("select * from user where user_name = #{userName}")
    User findUserByUserName(String userName);

    @Select("select * from user where email = #{email}")
    User findUserByEmail(String email);
}
