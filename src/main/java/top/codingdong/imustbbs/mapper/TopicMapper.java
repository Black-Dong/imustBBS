package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;
import top.codingdong.imustbbs.po.Topic;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/27 14:34
 */
public interface TopicMapper extends Mapper<Topic> {

    @Update("update t_topic set public_status = 0 where id = #{id}")
    void modifyPublicStatusToFalse(Long id);

    @Select("select * from t_topic where public_status = true")
    @Results(id = "selectTopicAndUser", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id", property = "user",
                    one = @One(select = "top.codingdong.imustbbs.mapper.UserMapper.selectByPrimaryKey")),
            @Result(column = "category_id",property = "category",
                    one = @One(select = "top.codingdong.imustbbs.mapper.CategoryMapper.selectByPrimaryKey"))
    })
    List<Topic> listAndUserAndCategory();

    @Select("select * from t_topic where id = #{id}")
    @ResultMap("selectTopicAndUser")
    Topic selectAndUserAndCategoryById(Integer id);
}
