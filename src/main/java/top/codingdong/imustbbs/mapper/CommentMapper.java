package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.codingdong.imustbbs.model.Comment;

/**
 * @author Dong
 * @date 2020/2/11 22:57
 */
@Mapper
public interface CommentMapper {

    @Insert("insert into comment(parent_id, type, commentator, create_time, update_time,content) value(#{parentId}, #{type}, #{commentator}, #{createTime}, #{updateTime}, #{content})")
    int insert(Comment comment);
}
