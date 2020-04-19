package top.codingdong.imustbbs.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.mapper.ReplyMapper;
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
    private ReplyMapper replyMapper;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private RedisSerializer redisSerializer = new StringRedisSerializer();

    @Override
    public List<Topic> list(Integer pageNumber, Integer pageSize) {

        PageHelper.startPage(pageNumber, pageSize, "last_reply_time desc");
        List<Topic> topicPage = topicMapper.selectAll();

        return topicPage;
    }

    @Override
    public List<Topic> list(Integer pageNumber, Integer pageSize, Integer userId) {

        Example example = new Example(Topic.class);
        example.createCriteria()
                .andEqualTo("userId", userId)
                .andEqualTo("publicStatus", true);

        PageHelper.startPage(pageNumber, pageSize, "last_reply_time desc");
        List<Topic> topics = topicMapper.selectByExample(example);
        return topics;
    }

    @Override
    public List<Topic> listAndUserAndCategory(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize, "last_reply_time desc");
        List<Topic> topics = topicMapper.listAndUserAndCategory();

        topics.forEach(topic -> {
            topic.setReplyCount(replyMapper.selectCountByTopicId(topic.getId()));
        });
        return topics;
    }

    @Override
    public Topic selectAndUserAndCategoryById(Integer id) {
        Topic topic = topicMapper.selectAndUserAndCategoryById(id);
        topic.setReplyCount(replyMapper.selectCountByTopicId(topic.getId()));
        return topic;
    }

    @Override
    public void save(Topic topic) {
        if (topic.getPublicStatus()) {
            // 把审核通过的放入redis
            redisTemplate.setKeySerializer(redisSerializer);
            redisTemplate.opsForValue().set("topic_" + topic.getId(), topic);
        }
        topicMapper.insertSelective(topic);
    }

    @Override
    public void update(Topic topic) {
        if (topic.getPublicStatus()) {
            // 把审核通过的放入redis
            redisTemplate.setKeySerializer(redisSerializer);
            redisTemplate.opsForValue().set("topic_" + topic.getId(), topic);
        }
        topicMapper.updateByPrimaryKeySelective(topic);
    }

    @Override
    public void deleteById(Integer id) {

        try {
            redisTemplate.delete("topic_" + id);
            topicMapper.modifyPublicStatusToFalse(id);
        } catch (Exception e) {
            // todo: 删除的帖子不存在时抛出异常
            System.err.println("删除错误");
        }

    }

    @Override
    public Topic findById(Integer id) {
        if (redisTemplate.hasKey("topic_" + id)) {
            return (Topic) redisTemplate.opsForValue().get("topic_" + id);
        } else {
            Topic topic = topicMapper.selectByPrimaryKey(id);
            if (topic.getPublicStatus()) {
                redisTemplate.setKeySerializer(redisSerializer);
                redisTemplate.opsForValue().set("topic_" + id, topic);
            }
            return topic;
        }
    }

    @Override
    public List<Topic> listAndUserAndCategoryByCategoryId(Integer pageNumber, Integer pageSize, Integer id) {

        PageHelper.startPage(pageNumber, pageSize, "last_reply_time desc");
        return topicMapper.listAndUserAndCategoryByCategoryId(id);

    }

    @Override
    public void topTopicById(Integer topicId, boolean topStatus) {
        if (topStatus) {
            topicMapper.modifyTopStatusToFalse(topicId);
        } else {
            topicMapper.modifyTopStatusToTrue(topicId);
        }
    }

    @Override
    public void boutiqueTopicById(Integer topicId, boolean boutiqueTopic) {
        if (boutiqueTopic) {
            topicMapper.modifyBoutiqueStatusToFalse(topicId);
        } else {
            topicMapper.modifyBoutiqueStatusToTrue(topicId);
        }
    }

    @Override
    public List<Topic> listLikeTitle(String title, Integer pageNumber) {

        PageHelper.startPage(pageNumber, 10,"create_time desc");
        return topicMapper.listLikeTitle(title);
    }
}
