package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import top.codingdong.imustbbs.model.Post;

/**
 * @author Dong
 * @date 2020/1/25 14:12
 */
@Mapper
public interface PostMapper {

    @Insert("insert into post(title,description,create_time,update_time,creator,tag) values(#{title}, #{description}, #{createTime}, #{updateTime}, #{creator}, #{tag})")
    void create(Post post);
}
