package top.codingdong.imustbbs.service;

import com.github.pagehelper.PageInfo;
import top.codingdong.imustbbs.model.Post;

/**
 * @author Dong
 * @date 2020/1/26 14:05
 */
public interface PostService {

    void createOrUpdate(Post post);

    PageInfo<Post> listPost(Integer pageNum, Integer size);

    PageInfo<Post> listByUserId(Long userId, Integer pageNumber, Integer size);

    Post getById(Long id);

    Post viewPostById(Long id);

    void incCommentCount(Post parent_post);
}
