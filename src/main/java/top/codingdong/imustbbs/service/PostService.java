package top.codingdong.imustbbs.service;

import com.github.pagehelper.PageInfo;
import top.codingdong.imustbbs.dto.PostDto;

/**
 * @author Dong
 * @date 2020/1/26 14:05
 */
public interface PostService {

    void createOrUpdate(PostDto post);

    PageInfo<PostDto> listPost(Integer pageNum, Integer size);

    PageInfo<PostDto> listByUserId(Long userId, Integer pageNumber, Integer size);

    PostDto getById(Long id);

    PostDto viewPostById(Long id);

    void incCommentCount(PostDto parent_post);
}
