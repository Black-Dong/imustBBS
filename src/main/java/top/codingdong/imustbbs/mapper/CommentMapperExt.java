package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.*;
import top.codingdong.imustbbs.dto.CommentDto;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/11 22:57
 */
@Mapper
public interface CommentMapperExt {

    @Insert("insert into comment(parent_id, type, commentator, create_time, update_time,content) value(#{parentId}, #{type}, #{commentator}, #{createTime}, #{updateTime}, #{content})")
    int insert(CommentDto comment);


    @Select("select * from comment where type = #{type} and parent_id = #{id} order by create_time desc")
    @Results(id = "selectCommentAndUser", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "commentator", property = "user", one = @One(select = "top.codingdong.imustbbs.mapper.UserMapper.selectByPrimaryKey")),
    })
    List<CommentDto> listByIdAndType(Long id, Integer type);
}
