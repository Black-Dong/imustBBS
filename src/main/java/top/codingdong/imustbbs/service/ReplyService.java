package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.po.Reply;

import java.util.List;

/**
 * @author Dong
 * @date 2020/3/2 11:24
 */
public interface ReplyService {

    List<Reply> selectAndUserByTopicId(Integer id);
}
