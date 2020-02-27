package top.codingdong.imustbbs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import top.codingdong.imustbbs.entity.TopicType;

import java.util.List;

/**
 * 帖子类型service
 *
 * @author Dong
 * @date 2020/2/27 14:36
 */
public interface TopicTypeService {

    /**
     * 分页查询帖子类型列表
     * @param pageNumber
     * @param pageSize
     * @param direction
     * @param properties
     * @return
     */
    Page<TopicType> list(Integer pageNumber, Integer pageSize, Sort.Direction direction, String... properties);

    /**
     * 查询帖子列表
     * @param direction
     * @param properties
     * @return
     */
    List<TopicType> listAll(Sort.Direction direction, String... properties);

    /**
     * 获取帖子类型总数
     * @return
     */
    Long getCount();

    /**
     * 新增和修改帖子类型
     * @param topicType
     */
    void save(TopicType topicType);

    /**
     * 根据Id删除帖子分类
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据Id查找帖子分类
     * @param id
     * @return
     */
    TopicType findById(Integer id);
}
