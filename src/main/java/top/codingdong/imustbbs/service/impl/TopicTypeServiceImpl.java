package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.entity.TopicType;
import top.codingdong.imustbbs.repository.TopicTypeRepository;
import top.codingdong.imustbbs.service.TopicTypeService;
import top.codingdong.imustbbs.util.Constants;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/27 14:36
 */
@Service
@Transactional
public class TopicTypeServiceImpl implements TopicTypeService {

    @Autowired
    private TopicTypeRepository topicTypeRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Page<TopicType> list(Integer pageNumber, Integer pageSize, Sort.Direction direction, String... properties) {
        Page<TopicType> topicTypePage = topicTypeRepository.findAll(PageRequest.of(pageNumber, pageSize, direction, properties));
        return topicTypePage;
    }

    @Override
    public List<TopicType> listAll(Sort.Direction direction, String... properties) {
        if (redisTemplate.hasKey(Constants.ALL_TOPIC_TYPE_NAME)) {
            return redisTemplate.opsForList().range(Constants.ALL_TOPIC_TYPE_NAME, 0, -1);
        } else {
            return topicTypeRepository.findAll(Sort.by(direction, properties));
        }
    }

    @Override
    public Long getCount() {
        return topicTypeRepository.count();
    }

    @Override
    public void save(TopicType topicType) {
        boolean flag = false;
        if (topicType.getTopicTypeId() == null) {
            flag = true;
        }
        topicTypeRepository.save(topicType);
        if (flag) {
            redisTemplate.opsForList().rightPush(Constants.ALL_TOPIC_TYPE_NAME, topicType);
        }
    }

    @Override
    public void delete(Integer id) {
        topicTypeRepository.deleteById(id);
    }

    @Override
    public TopicType findById(Integer id) {
        return topicTypeRepository.getOne(id);
    }
}
