package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import top.codingdong.imustbbs.model.Topic;

/**
 * @author Dong
 * @date 2020/2/19 9:30
 */
public interface TopicMapper extends Mapper<Topic> {

    @Update("update t_topic set public_status = 0 where id = #{id}")
    void updatePublicStatus(Long id);
}
