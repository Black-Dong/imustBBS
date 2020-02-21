package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.mapper.ReplyMapper;
import top.codingdong.imustbbs.model.Reply;
import top.codingdong.imustbbs.service.ReplyService;

/**
 * @author Dong
 * @date 2020/2/21 21:27
 */
@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public ResultDTO<Reply> createReply(Reply reply) {

        reply.setCreateTime(System.currentTimeMillis());
        replyMapper.insertSelective(reply);


        return ResultDTO.success();
    }
}
