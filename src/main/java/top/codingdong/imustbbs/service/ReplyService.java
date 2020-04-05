package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.po.Reply;

import java.util.List;

/**
 * 回复接口
 *
 * @author Dong
 * @date 2020/3/2 11:24
 */
public interface ReplyService {

    /**
     * 根据帖子id查找回复（包括回复人信息）
     *
     * @param id 帖子id
     * @return
     */
    List<Reply> listAndUserByTopicId(Integer id);

    /**
     * 根据userId，查找回复列表
     *
     * @param userId
     * @param pageNumber
     * @return
     */
    List<Reply> listAndTopicByUserId(Integer userId, Integer pageNumber, Integer pageSize);
}
