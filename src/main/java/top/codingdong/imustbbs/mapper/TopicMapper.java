package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;
import top.codingdong.imustbbs.po.Topic;

import java.util.List;

/**
 * 帖子mapper
 *
 * @author Dong
 * @date 2020/2/27 14:34
 */
public interface TopicMapper extends Mapper<Topic> {

    /**
     * 修改帖子公开状态
     *
     * @param id
     */
    @Update("update t_topic set public_status = 0 where id = #{id}")
    void modifyPublicStatusToFalse(Integer id);

    /**
     * 查询帖子列表，包含发帖人信息 所在分类信息
     *
     * @return
     */
    @Select("select * from t_topic where public_status = true")
    @Results(id = "listAndUserAndCategory", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "user_id", property = "user",
                    one = @One(select = "top.codingdong.imustbbs.mapper.UserMapper.selectByPrimaryKey")),
            @Result(column = "category_id", property = "category",
                    one = @One(select = "top.codingdong.imustbbs.mapper.CategoryMapper.selectByPrimaryKey"))
    })
    List<Topic> listAndUserAndCategory();

    /**
     * 根据帖子id查询帖子信息，包含发帖人信息 所在分类信息
     *
     * @param id
     * @return
     */
    @Select("select * from t_topic where id = #{id}")
    @ResultMap("listAndUserAndCategory")
    Topic selectAndUserAndCategoryById(Integer id);

    /**
     * 根据分类id查询帖子列表，包含发帖人信息 所在分类信息
     *
     * @param id
     * @return
     */
    @Select("select * from t_topic where category_id = #{id}")
    @ResultMap("listAndUserAndCategory")
    List<Topic> listAndUserAndCategoryByCategoryId(Integer id);

    /**
     * 根据帖子id查询帖子
     *
     * @param id
     * @return
     */
    @Select("select * from t_topic where id = #{id}")
    Topic selectById(Integer id);

    /**
     * 修改置顶状态为不置顶
     *
     * @param topicId
     */
    @Update("update t_topic set top_status = 0 where id = #{topicId}")
    void modifyTopStatusToFalse(Integer topicId);

    /**
     * 修改置顶状态为置顶
     *
     * @param topicId
     */
    @Update("update t_topic set top_status = 1 where id = #{topicId}")
    void modifyTopStatusToTrue(Integer topicId);

    /**
     * 修改精品状态为不是精品
     *
     * @param topicId
     */
    @Update("update t_topic set boutique_status = 0 where id = #{topicId}")
    void modifyBoutiqueStatusToFalse(Integer topicId);

    /**
     * 修改精品状态为是精品
     *
     * @param topicId
     */
    @Update("update t_topic set boutique_status = 1 where id = #{topicId}")
    void modifyBoutiqueStatusToTrue(Integer topicId);

    /**
     * 根据帖子标题模糊查询
     *
     * @param title
     * @return
     */
    @Select("select * from t_topic where title like concat('%',#{title},'%')")
    @ResultMap(value = "listAndUserAndCategory")
    List<Topic> listLikeTitle(String title);
}
