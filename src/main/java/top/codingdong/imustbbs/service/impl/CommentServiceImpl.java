package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.mapper.CommentMapperExt;
import top.codingdong.imustbbs.mapper.PostMapperExt;
import top.codingdong.imustbbs.dto.CommentDto;
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
    private CommentMapperExt commentMapper;

    @Autowired
    private PostMapperExt postMapper;

    @Override
    public int insert(CommentDto comment) {

        comment.setCreateTime(System.currentTimeMillis());
        comment.setUpdateTime(System.currentTimeMillis());

        return commentMapper.insert(comment);
    }

    @Override
    public CommentDto getById(Long id) {
        return commentMapper.getById(id);
    }

    @Override
    public List<CommentDto> listByPostId(Long id) {
        List<CommentDto> comments = commentMapper.listByPostId(id);
        return null;
    }
}
