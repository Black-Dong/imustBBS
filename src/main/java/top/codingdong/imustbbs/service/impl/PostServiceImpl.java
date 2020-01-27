package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.mapper.PostMapper;
import top.codingdong.imustbbs.mapper.UserMapper;
import top.codingdong.imustbbs.model.Post;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.PostService;

import java.util.List;

/**
 * @author Dong
 * @date 2020/1/26 14:05
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public void create(Post post) {
        postMapper.create(post);
    }

    @Override
    public List<Post> listPost() {
        List<Post> posts = postMapper.listPost();
        /*for (Post post : posts) {
            User user = userMapper.findById(post.getCreator());
            post.setUser(user);
        }*/
        return posts;
    }
}
