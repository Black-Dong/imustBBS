package top.codingdong.imustbbs.service;

import com.github.pagehelper.PageInfo;
import top.codingdong.imustbbs.model.Post;

/**
 * @author Dong
 * @date 2020/1/26 14:05
 */
public interface PostService {

    void create(Post post);

    PageInfo<Post> listPost(Integer pageSize, Integer size);
}
