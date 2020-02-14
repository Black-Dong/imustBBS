package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.mapper.CommentMapper;
import top.codingdong.imustbbs.mapper.PostMapper;
import top.codingdong.imustbbs.model.Comment;
import top.codingdong.imustbbs.service.CommentService;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/11 23:16
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostMapper postMapper;

    @Override
    public int insert(Comment comment) {

        comment.setCreateTime(System.currentTimeMillis());
        comment.setUpdateTime(System.currentTimeMillis());

        return commentMapper.insert(comment);
    }

    @Override
    public Comment getById(Long id) {
        return commentMapper.getById(id);
    }

    @Override
    public List<Comment> listByPostId(Long id) {
        List<Comment> comments = commentMapper.listByPostId(id);
        return null;
    }
}
