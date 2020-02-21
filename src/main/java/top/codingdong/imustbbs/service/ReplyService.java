package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.model.Reply;

/**
 * @author Dong
 * @date 2020/2/21 21:25
 */
public interface ReplyService {

    ResultDTO<Reply> createReply(Reply reply);
}
