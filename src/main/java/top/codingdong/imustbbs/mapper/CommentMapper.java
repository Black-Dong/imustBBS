package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import top.codingdong.imustbbs.model.Comment;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/11 22:57
 */
@Mapper
public interface CommentMapper {

    @Insert("insert into comment(parent_id, type, commentator, create_time, update_time,content) value(#{parentId}, #{type}, #{commentator}, #{createTime}, #{updateTime}, #{content})")
    int insert(Comment comment);

    @Select("select * from comment where id = #{id} limit 1")
    Comment getById(Long id);

    @Select("select * from comment where type = 1 and parent_id = #{id}")
    @Results(id = "selectCommentAndUser",value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "commentator", property = "user", one = @One(select = "top.codingdong.imustbbs.mapper.UserMapper.findById", fetchType = FetchType.LAZY)),
    })
    List<Comment> listByPostId(Long id);
}
