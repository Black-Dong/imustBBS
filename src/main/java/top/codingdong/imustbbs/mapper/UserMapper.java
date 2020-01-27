package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import top.codingdong.imustbbs.model.User;

/**
 * @author Dong
 * @date 2020/1/24 15:37
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,source,avatar_url,token,create_time,update_time) values (#{name},#{accountId},#{source},#{avatarUrl},#{token},#{createTime},#{updateTime})")
    void insertUser(User user);

    @Select("select * from user where token = #{token} limit 1")
    User findByToken(String token);

    @Select("select * from user where account_id = #{accountId} and source = #{source} limit 1")
    User getUserByAccountAndSource(String accountId, String source);

    @Update("update user set avatar_url = #{avatarUrl}, token = #{token}, update_time = #{updateTime} where id = #{id}")
    void updateUserSource(User user);


}
