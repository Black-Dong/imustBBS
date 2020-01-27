package top.codingdong.imustbbs.service;

import top.codingdong.imustbbs.model.Post;

import java.util.List;

/**
 * @author Dong
 * @date 2020/1/26 14:05
 */
public interface PostService {

    void create(Post post);

    List<Post> listPost();
}
