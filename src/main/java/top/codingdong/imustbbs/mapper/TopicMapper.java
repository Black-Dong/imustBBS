package top.codingdong.imustbbs.mapper;

import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;
import top.codingdong.imustbbs.po.Topic;

/**
 * @author Dong
 * @date 2020/2/27 14:34
 */
public interface TopicMapper extends Mapper<Topic> {

    @Update("update t_topic set public_status = 0 where id = #{id}")
    void modifyPublicStatusToFalse(Long id);
}
