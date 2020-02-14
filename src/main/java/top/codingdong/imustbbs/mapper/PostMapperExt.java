package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import top.codingdong.imustbbs.dto.PostDto;

import java.util.List;

/**
 * @author Dong
 * @date 2020/1/25 14:12
 */
@Mapper
public interface PostMapperExt {

    @Insert("insert into post(title,description,create_time,update_time,creator,tag) values(#{title}, #{description}, #{createTime}, #{updateTime}, #{creator}, #{tag})")
    int create(PostDto post);

    @Select("select * from post")
    @Results(id = "selectPostAndUser",value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "creator",property = "user", one = @One(select = "top.codingdong.imustbbs.mapper.UserMapperExt.findById",fetchType = FetchType.LAZY)),
    })
    List<PostDto> listPost();

    @Select("select * from post where creator = #{userId}")
//    @ResultMap(value = "selectPostAndUser")
    List<PostDto> listByUserId(Long userId);

    @Select("select * from post where id = #{id} limit 1")
    @ResultMap(value = "selectPostAndUser")
    PostDto getById(Long id);

    @Update("update post set title=#{title},description=#{description}, update_time=#{updateTime}, creator=#{creator},tag=#{tag} where id =#{id}")
    int update(PostDto post);

    @Update("update post set view_count = view_count + #{viewCount,jdbcType=INTEGER} where id = #{id}")
    int incViewCount(PostDto post);

    @Update("update post set comment_count = comment_count + #{commentCount,jdbcType=INTEGER} where id = #{id}")
    int incCommentCount(PostDto post);
}
