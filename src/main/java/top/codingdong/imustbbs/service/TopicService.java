package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.model.Topic;

/**
 * @author Dong
 * @date 2020/2/19 11:00
 */
public interface TopicService {

    ResultDTO<Topic> createTopic(Topic topic);

    ResultDTO updateTopic(Topic topic);

    Topic findTopicById(Integer id);
}
