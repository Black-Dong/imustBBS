package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;
import top.codingdong.imustbbs.po.Reply;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/28 17:40
 */
public interface ReplyMapper extends Mapper<Reply> {

    @Select("select * from t_reply where topic_id = #{topicId}")
    @Results(id = "selectAndUser", value = {
            @Result(column = "user_id", property = "user", one = @One(select = "top.codingdong.imustbbs.mapper.UserMapper.selectByPrimaryKey"))
    })
    List<Reply> listAndUserByTopicId(Integer topicId);
}
