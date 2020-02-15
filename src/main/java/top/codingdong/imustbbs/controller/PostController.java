package top.codingdong.imustbbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.codingdong.imustbbs.dto.CommentDto;
import top.codingdong.imustbbs.dto.PostDto;
import top.codingdong.imustbbs.enums.CommentEnum;
import top.codingdong.imustbbs.service.CommentService;
import top.codingdong.imustbbs.service.PostService;

import java.util.List;

/**
 * @author Dong
 * @date 2020/1/28 21:14
 */
@Controller
@Api(description = "帖子浏览")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{id}")
    @ApiOperation(value = "帖子详情页")
    public String post(@PathVariable(name = "id")Long id,
                       Model model){

        PostDto post = postService.viewPostById(id);
        post.setCreator(post.getUser().getId());

        List<CommentDto> comments = commentService.listReplyByIdAndType(post.getId(), CommentEnum.REPLY_POST);

        model.addAttribute("post",post);
        model.addAttribute("comments",comments);
        return "post";
    }


}
