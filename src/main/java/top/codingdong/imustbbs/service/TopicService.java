package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.po.Topic;

import java.util.List;

/**
 * 帖子类型service
 *
 * @author Dong
 * @date 2020/2/27 14:36
 */
public interface TopicService {

    /**
     * 根据分页条件查询帖子列表
     *
     * @param pageNumber 页码
     * @param pageSize   每页条数
     * @return
     */
    List<Topic> list(Integer pageNumber, Integer pageSize);

    /**
     * 根据用户id及分页条件查询帖子列表
     *
     * @param pageNumber
     * @param pageSize
     * @param userId
     * @return
     */
    List<Topic> list(Integer pageNumber, Integer pageSize, Integer userId);

    /**
     * 根据分页条件查询帖子及其包含的用户和分类
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<Topic> listAndUserAndCategory(Integer pageNumber, Integer pageSize);

    /**
     * 根据帖子id查找帖子列表（包括发帖人及帖子分类）
     *
     * @param id
     * @return
     */
    Topic selectAndUserAndCategoryById(Integer id);

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
    void deleteById(Integer id);

    /**
     * 根据Id查找帖子分类
     *
     * @param id
     * @return
     */
    Topic findById(Integer id);

    /**
     * 按分类查找帖子列表
     *
     * @param id
     * @return
     */
    List<Topic> listAndUserAndCategoryByCategoryId(Integer pageNumber, Integer pageSize, Integer id);

    /**
     * 修改帖子置顶状态
     *
     * @param topicId
     * @param topStatus
     */
    void topTopicById(Integer topicId, boolean topStatus);

    /**
     * 修改帖子精品状态
     *
     * @param topicId
     * @param boutiqueTopic
     */
    void boutiqueTopicById(Integer topicId, boolean boutiqueTopic);

    /**
     * 根据传入的title，模糊查询帖子标题，按pageNumber查询
     *
     * @param title
     * @param pageNumber
     * @return
     */
    List<Topic> listLikeTitle(String title, Integer pageNumber);
}
