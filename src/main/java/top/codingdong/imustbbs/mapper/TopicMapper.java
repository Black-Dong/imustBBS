package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.common.Mapper;
import top.codingdong.imustbbs.po.Topic;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/27 14:34
 */
public interface TopicMapper extends Mapper<Topic> {

    @Update("update t_topic set public_status = 0 where id = #{id}")
    void modifyPublicStatusToFalse(Integer id);

    @Select("select * from t_topic where public_status = true")
    @Results(id = "listAndUserAndCategory", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id", property = "user",
                    one = @One(select = "top.codingdong.imustbbs.mapper.UserMapper.selectByPrimaryKey")),
            @Result(column = "category_id",property = "category",
                    one = @One(select = "top.codingdong.imustbbs.mapper.CategoryMapper.selectByPrimaryKey"))
    })
    List<Topic> listAndUserAndCategory();

    @Select("select * from t_topic where id = #{id}")
    @ResultMap("listAndUserAndCategory")
    Topic selectAndUserAndCategoryById(Integer id);

    @Select("select * from t_topic where category_id = #{id}")
    @ResultMap("listAndUserAndCategory")
    List<Topic> listAndUserAndCategoryByCategoryId(Integer id);

    @Select("select * from t_topic where id = #{id}")
    Topic selectById(Integer id);

    @Update("update t_topic set top_status = 0 where id = #{topicId}")
    void modifyTopStatusToFalse(Integer topicId);

    @Update("update t_topic set top_status = 1 where id = #{topicId}")
    void modifyTopStatusToTrue(Integer topicId);
}
