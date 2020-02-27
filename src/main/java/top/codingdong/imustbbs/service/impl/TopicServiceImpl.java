package top.codingdong.imustbbs.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.entity.Topic;
import top.codingdong.imustbbs.entity.TopicType;
import top.codingdong.imustbbs.entity.User;
import top.codingdong.imustbbs.repository.TopicRepository;
import top.codingdong.imustbbs.service.TopicService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Dong
 * @date 2020/2/27 14:36
 */
@Service
@Transactional
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private RedisSerializer redisSerializer = new StringRedisSerializer();

    @Override
    public List<Topic> list(Topic topic, User user, String s_bpublishDate, String s_epublishDate, Integer pageNumber, Integer pageSize, Sort.Direction direction, String... properties) {
        Page<Topic> topicPage = topicRepository.findAll(getSpecification(topic, user, s_bpublishDate, s_epublishDate), PageRequest.of(pageNumber, pageSize, direction, properties));

        return topicPage.getContent();
    }

    @Override
    public Long getCount(Topic topic, User user, String s_bpublishDate, String s_epublishDate) {
        Long count = topicRepository.count(getSpecification(topic, user, s_bpublishDate, s_epublishDate));
        return count;
    }

    private Specification<Topic> getSpecification(Topic topic, User user, String s_bpublishDate, String s_epublishDate) {
        return new Specification<Topic>() {
            @Override
            public Predicate toPredicate(Root<Topic> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (StringUtils.isNotBlank(s_bpublishDate)) {
                    predicate.getExpressions().add(criteriaBuilder.greaterThanOrEqualTo(root.get("publishDate").as(String.class), s_bpublishDate));
                }
                if (StringUtils.isNotBlank(s_epublishDate)) {
                    predicate.getExpressions().add(criteriaBuilder.lessThan(root.get("publishDate").as(String.class), s_epublishDate));
                }
                if (user != null && StringUtils.isNotBlank(user.getNickname())) {
                    predicate.getExpressions().add(criteriaBuilder.like(root.get("user").get("nickname"), "%" + user.getNickname() + "%"));
                }
                if (topic != null) {
                    if (StringUtils.isNotBlank(topic.getName())) {
                        predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), "%" + topic.getName() + "%"));
                    }
                    if (topic.isHot()) {
                        predicate.getExpressions().add(criteriaBuilder.equal(root.get("isHot"), true));
                    }
                    if (topic.getTopicType() != null && topic.getTopicType().getTopicTypeId() != null) {
                        predicate.getExpressions().add(criteriaBuilder.equal(root.get("topicType").get("topicTypeId"), topic.getTopicType().getTopicTypeId()));
                    }
                    if (topic.getUser() != null && topic.getUser().getUserId() != null) {
                        predicate.getExpressions().add(criteriaBuilder.equal(root.get("user").get("userId"), topic.getUser().getUserId()));
                    }
                    if (topic.getState() != null) {
                        predicate.getExpressions().add(criteriaBuilder.equal(root.get("state"), topic.getState()));
                    }
                    if (!topic.isUseful()) {
                        predicate.getExpressions().add(criteriaBuilder.equal(root.get("isUseful"), false));
                    }
                }
                return predicate;
            }
        };
    }

    @Override
    public void save(Topic topic) {
        if (topic.getState() == 2) {
            // 把审核通过的放入redis
            redisTemplate.setKeySerializer(redisSerializer);
            redisTemplate.opsForValue().set("topic_" + topic.getTopicId(), topic);
        }
        topicRepository.save(topic);
    }

    @Override
    public void delete(Integer id) {

        redisTemplate.delete("topic_" + id);

        topicRepository.deleteById(id);
    }

    @Override
    public Topic findById(Integer id) {
        if (redisTemplate.hasKey("topic_" + id)) {
            return (Topic) redisTemplate.opsForValue().get("topic_" + id);
        }else{
            Topic topic = topicRepository.getOne(id);
            if (topic.getState() == 2){
                redisTemplate.setKeySerializer(redisSerializer);
                redisTemplate.opsForValue().set("topic_"+id,topic);
            }
            return topic;
        }
    }
}
