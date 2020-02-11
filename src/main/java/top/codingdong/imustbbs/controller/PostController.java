package top.codingdong.imustbbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.codingdong.imustbbs.model.Post;
import top.codingdong.imustbbs.service.PostService;

/**
 * @author Dong
 * @date 2020/1/28 21:14
 */
@Controller
@Api(description = "帖子浏览")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/post/{id}")
    @ApiOperation(value = "帖子详情页")
    public String post(@PathVariable(name = "id")Integer id,
                       Model model){

        Post post = postService.viewPostById(id);
        post.setCreator(post.getUser().getId());
        model.addAttribute("post",post);
        return "post";
    }
}
