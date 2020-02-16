package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.*;
import top.codingdong.imustbbs.dto.PostDto;
import top.codingdong.imustbbs.model.Post;

import java.util.List;

/**
 * @author Dong
 * @date 2020/1/25 14:12
 */
@Mapper
public interface PostMapperExt {

    @Select("select * from post")
    @Results(id = "selectPostAndUser",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "creator",property = "user", one = @One(select = "top.codingdong.imustbbs.mapper.UserMapper.selectByPrimaryKey")),
    })
    List<PostDto> listPostAndUser();

    @Select("select * from post where id = #{id} limit 1")
    @ResultMap(value = "selectPostAndUser")
    PostDto getPostAndUserById(Long id);

    @Update("update post set view_count = view_count + #{viewCount,jdbcType=INTEGER} where id = #{id}")
    int incViewCount(PostDto post);

    @Update("update post set comment_count = comment_count + #{commentCount,jdbcType=INTEGER} where id = #{id}")
    int incCommentCount(PostDto post);

    @Select("select * from post where id != #{id} and tag regexp #{tag} order by create_time desc limit 10")
    List<Post> selectRelated(Post post);
}
