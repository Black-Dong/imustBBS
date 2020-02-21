package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.mapper.TopicMapper;
import top.codingdong.imustbbs.model.Topic;
import top.codingdong.imustbbs.service.TopicService;

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
}
