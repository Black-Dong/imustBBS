package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.enums.CommentEnum;
import top.codingdong.imustbbs.mapper.CommentMapper;
import top.codingdong.imustbbs.mapper.CommentMapperExt;
import top.codingdong.imustbbs.mapper.PostMapperExt;
import top.codingdong.imustbbs.dto.CommentDto;
import top.codingdong.imustbbs.model.Comment;
import top.codingdong.imustbbs.model.CommentExample;
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
    private CommentMapperExt commentMapperExt;

    @Autowired
    private PostMapperExt postMapper;

    @Override
    public int insert(Comment comment) {

        comment.setCreateTime(System.currentTimeMillis());
        comment.setUpdateTime(System.currentTimeMillis());

        return commentMapper.insert(comment);
    }

    @Override
    public Comment getById(Long id) {
        return commentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CommentDto> listByPostId(Long id) {

        List<CommentDto> commentDtos = commentMapperExt.listByPostId(id,CommentEnum.REPLY_POST.getType());
        return commentDtos;
    }
}
