package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.codingdong.imustbbs.model.User;

/**
 * @author Dong
 * @date 2020/1/24 15:37
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user (name,account_id,token,create_time,update_time) values (#{name},#{accountId},#{token},#{createTime},#{updateTime})")
    void insertUser(User user);
}
