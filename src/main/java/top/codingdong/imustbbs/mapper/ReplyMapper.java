package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import top.codingdong.imustbbs.po.Reply;

import java.util.List;

/**
 * 回复mapper
 *
 * @author Dong
 * @date 2020/2/28 17:40
 */
public interface ReplyMapper extends Mapper<Reply> {

    /**
     * 根据帖子id，查询回复列表，包括回复人信息
     *
     * @param topicId
     * @return
     */
    @Select("select * from t_reply where topic_id = #{topicId}")
    @Results(id = "selectAndUser", value = {
            @Result(column = "user_id", property = "user",
                    one = @One(select = "top.codingdong.imustbbs.mapper.UserMapper.selectByPrimaryKey"))
    })
    List<Reply> listAndUserByTopicId(Integer topicId);

    /**
     * 根据用户id，查询回复列表，包括帖子信息
     *
     * @param id
     * @return
     */
    @Select("select * from t_reply where user_id = #{id}")
    @Results(id = "selectAndTopic", value = {
            @Result(column = "topic_id", property = "topic",
                    one = @One(select = "top.codingdong.imustbbs.mapper.TopicMapper.selectById"))
    })
    List<Reply> listAndTopicByUserId(Integer id);

    /**
     * 根据帖子id查询帖子回复数
     * @param id
     * @return
     */
    @Select("select count(id) from t_reply where topic_id = #{id}")
    Integer selectCountByTopicId(Integer id);
}
