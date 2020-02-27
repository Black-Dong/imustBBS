package top.codingdong.imustbbs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import top.codingdong.imustbbs.entity.Topic;
import top.codingdong.imustbbs.entity.TopicType;
import top.codingdong.imustbbs.entity.User;

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
     * @param topic          帖子信息
     * @param user           用户
     * @param s_bpublishDate 发布开始时间
     * @param s_epublishDate 发布结束时间
     * @param pageNumber     页码
     * @param pageSize       每页条数
     * @param direction      排序
     * @param properties     排序字段
     * @return
     */
    List<Topic> list(Topic topic, User user, String s_bpublishDate, String s_epublishDate,
                     Integer pageNumber, Integer pageSize, Sort.Direction direction, String... properties);

    /**
     * 根据条件获取总记录数
     *
     * @param topic         帖子信息
     * @param user          用户
     * @param s_bpublishDate    发布开始时间
     * @param s_epublishDate    发布结束时间
     * @return
     */
    Long getCount(Topic topic, User user, String s_bpublishDate, String s_epublishDate);

    /**
     * 新增和修改帖子
     * @param topic
     */
    void save(Topic topic);

    /**
     * 根据Id删除帖子分类
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据Id查找帖子分类
     *
     * @param id
     * @return
     */
    Topic findById(Integer id);
}
