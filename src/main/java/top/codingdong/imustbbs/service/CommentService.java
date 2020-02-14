package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.dto.CommentDto;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/11 23:14
 */
public interface CommentService {

    int insert(CommentDto comment);

    CommentDto getById(Long id);

    List<CommentDto> listByPostId(Long id);
}
