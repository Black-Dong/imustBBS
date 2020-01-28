package top.codingdong.imustbbs.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.mapper.PostMapper;
import top.codingdong.imustbbs.model.Post;
import top.codingdong.imustbbs.service.PostService;

/**
 * @author Dong
 * @date 2020/1/26 14:05
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Override
    public void create(Post post) {
        postMapper.create(post);
    }

    @Override
    public PageInfo<Post> listPost(Integer pageNum, Integer size) {

        PageHelper.startPage(pageNum, size, "update_time desc");
        PageInfo<Post> pageInfo = PageInfo.of(postMapper.listPost());
        return pageInfo;
    }

    @Override
    public PageInfo<Post> listByUserId(Integer userId, Integer pageNumber, Integer size) {
        PageHelper.startPage(pageNumber, size, "update_time desc");
        PageInfo<Post> pageInfo = PageInfo.of(postMapper.listByUserId(userId));
        return pageInfo;
    }

    @Override
    public Post getById(Integer id) {
        return postMapper.getById(id);
    }
}
