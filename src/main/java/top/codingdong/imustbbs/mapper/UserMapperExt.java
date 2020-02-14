package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.codingdong.imustbbs.model.User;

/**
 * @author Dong
 * @date 2020/2/14 16:01
 */
public interface UserMapperExt {

    @Select("select * from user where token = #{token} limit 1")
    User findByToken(String token);

    @Select("select * from user where account_id = #{accountId} and source = #{source} limit 1")
    User getUserByAccountAndSource(String accountId, String source);

    @Update("update user set avatar_url = #{avatarUrl}, token = #{token}, update_time = #{updateTime} where id = #{id}")
    void updateUserSource(User user);
}

