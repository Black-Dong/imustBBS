package top.codingdong.imustbbs.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.mapper.ReplyMapper;
import top.codingdong.imustbbs.po.Reply;
import top.codingdong.imustbbs.service.ReplyService;

import java.util.List;

/**
 * 回复接口实现类
 *
 * @author Dong
 * @date 2020/3/2 11:26
 */
@Service
@Transactional
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;

    @Override
    public List<Reply> listAndUserByTopicId(Integer id) {
        return replyMapper.listAndUserByTopicId(id);
    }

    @Override
    public List<Reply> listAndTopicByUserId(Integer id, Integer pageNumber, Integer pageSize) {

        PageHelper.startPage(pageNumber, pageSize);
        return replyMapper.listAndTopicByUserId(id);
    }
}
