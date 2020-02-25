package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.model.Topic;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/19 11:00
 */
public interface TopicService {

    ResultDTO<Topic> createTopic(Topic topic);

    ResultDTO updateTopic(Topic topic);

    Topic findTopicById(Long id);

    ResultDTO removeTopicById(Long id);

    List<Topic> findsByCategoryId(Integer pageNumber, Integer pageSize, Long categoryId);

    List<Topic> findAll(Integer pageNumber, Integer pageSize);
}
