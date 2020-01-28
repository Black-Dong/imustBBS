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
    public PageInfo<Post> listPost(Integer pageSize,Integer size) {

        PageHelper.startPage(pageSize, size);
        PageInfo<Post> pageInfo = PageInfo.of(postMapper.listPost());
        return pageInfo;
    }
}
