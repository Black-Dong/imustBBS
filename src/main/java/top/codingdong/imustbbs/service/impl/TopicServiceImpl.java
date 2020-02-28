package top.codingdong.imustbbs.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.mapper.TopicMapper;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.service.TopicService;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/27 14:36
 */
@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private RedisSerializer redisSerializer = new StringRedisSerializer();

    @Override
    public List<Topic> list(Integer pageNumber, Integer pageSize) {

        PageHelper.startPage(pageNumber, pageSize,"last_reply_time desc");
        List<Topic> topicPage = topicMapper.selectAll();

        return topicPage;
    }

    @Override
    public List<Topic> list(Integer pageNumber, Integer pageSize, Long userId) {

        Example example = new Example(Topic.class);
        example.createCriteria()
                .andEqualTo("userId", userId);

        PageHelper.startPage(pageNumber, pageSize,"last_reply_time desc");
        List<Topic> topics = topicMapper.selectByExample(example);
        return topics;
    }

    @Override
    public void save(Topic topic) {
        if (topic.isPublicStatus()) {
            // 把审核通过的放入redis
            redisTemplate.setKeySerializer(redisSerializer);
            redisTemplate.opsForValue().set("topic_" + topic.getId(), topic);
        }
        topicMapper.insertSelective(topic);
    }

    @Override
    public void update(Topic topic) {
        if (topic.isPublicStatus()) {
            // 把审核通过的放入redis
            redisTemplate.setKeySerializer(redisSerializer);
            redisTemplate.opsForValue().set("topic_" + topic.getId(), topic);
        }
        topicMapper.updateByPrimaryKeySelective(topic);
    }

    @Override
    public void delete(Long id) {

        redisTemplate.delete("topic_" + id);

        topicMapper.modifyPublicStatusToFalse(id);
    }

    @Override
    public Topic findById(Integer id) {
        if (redisTemplate.hasKey("topic_" + id)) {
            return (Topic) redisTemplate.opsForValue().get("topic_" + id);
        } else {
            Topic topic = topicMapper.selectByPrimaryKey(id);
            if (topic.isPublicStatus()) {
                redisTemplate.setKeySerializer(redisSerializer);
                redisTemplate.opsForValue().set("topic_" + id, topic);
            }
            return topic;
        }
    }
}
