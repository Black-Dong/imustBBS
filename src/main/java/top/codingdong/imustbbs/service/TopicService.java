package top.codingdong.imustbbs.service;

import org.springframework.data.domain.Sort;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.po.User;

import java.util.List;

/**
 * 帖子类型service
 *
 * @author Dong
 * @date 2020/2/27 14:36
 */
public interface TopicService {

    /**
     * 根据分页条件分页查询帖子列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页条数
     * @return
     */
    List<Topic> list(Integer pageNumber, Integer pageSize);

    List<Topic> list(Integer pageNumber, Integer pageSize,Long userId);

    /**
     * 新增帖子
     *
     * @param topic
     */
    void save(Topic topic);

    /**
     * 修改帖子
     *
     * @param topic
     */
    void update(Topic topic);

    /**
     * 根据Id删除帖子分类
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 根据Id查找帖子分类
     *
     * @param id
     * @return
     */
    Topic findById(Integer id);
}
