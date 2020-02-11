package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.mapper.CommentMapper;
import top.codingdong.imustbbs.model.Comment;
import top.codingdong.imustbbs.service.CommentService;

/**
 * @author Dong
 * @date 2020/2/11 23:16
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public int insert(Comment comment) {
        return commentMapper.insert(comment);
    }
}
