package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import top.codingdong.imustbbs.model.Post;

import java.util.List;

/**
 * @author Dong
 * @date 2020/1/25 14:12
 */
@Mapper
public interface PostMapper {

    @Insert("insert into post(title,description,create_time,update_time,creator,tag) values(#{title}, #{description}, #{createTime}, #{updateTime}, #{creator}, #{tag})")
    void create(Post post);

    @Select("select * from post")
    @Results(id = "selectPostAndUser",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "creator",property = "user", one = @One(select = "top.codingdong.imustbbs.mapper.UserMapper.findById",fetchType = FetchType.LAZY)),
    })
    List<Post> listPost();

    @Select("select * from post where creator = #{userId}")
//    @ResultMap(value = "selectPostAndUser")
    List<Post> listByUserId(Integer userId);

    @Select("select * from post where id = #{id} limit 1")
    @ResultMap(value = "selectPostAndUser")
    Post getById(Integer id);
}
