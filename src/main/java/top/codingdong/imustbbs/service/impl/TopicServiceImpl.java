package top.codingdong.imustbbs.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.mapper.TopicMapper;
import top.codingdong.imustbbs.model.Topic;
import top.codingdong.imustbbs.service.TopicService;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/19 11:00
 */
@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;


    @Override
    public ResultDTO<Topic> createTopic(Topic topic) {
        topic.setCreateTime(System.currentTimeMillis());
        topic.setUpdateTime(System.currentTimeMillis());
        topic.setLastReplyTime(System.currentTimeMillis());

        topicMapper.insertSelective(topic);
        return ResultDTO.success();
    }

    @Override
    public ResultDTO updateTopic(Topic topic) {

        topic.setUpdateTime(System.currentTimeMillis());
        topic.setLastReplyTime(System.currentTimeMillis());
        topicMapper.updateByPrimaryKeySelective(topic);
        return ResultDTO.success();
    }

    @Override
    public Topic findTopicById(Long id) {
        return topicMapper.selectByPrimaryKey(id);
    }

    @Override
    public ResultDTO removeTopicById(Long id) {

        topicMapper.updatePublicStatus(id);
        return ResultDTO.success();
    }

    @Override
    public List<Topic> findsByCategoryId(Integer pageNumber, Integer pageSize, Long categoryId) {
        Example topicExample = new Example(Topic.class);
        topicExample.createCriteria()
                .andEqualTo("categoryId", categoryId);
        PageHelper.startPage(pageSize, pageNumber, "last_reply_time desc");
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        return topics;
    }

    @Override
    public List<Topic> findAll(Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize, "last_reply_time desc");
        List<Topic> topics = topicMapper.selectAll();
        return topics;
    }
}
