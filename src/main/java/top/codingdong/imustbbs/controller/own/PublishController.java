package top.codingdong.imustbbs.controller.own;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.codingdong.imustbbs.dto.PostDto;
import top.codingdong.imustbbs.model.Post;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.PostService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dong
 * @date 2020/1/25 10:24
 */
@Controller
@Api(description = "发布控制")
@RequestMapping("/own")
public class PublishController {

    @Autowired
    private PostService postService;

    @GetMapping("/editPost/{id}")
    public String edit(@PathVariable(name = "id")Long id,
                       Model model){
        PostDto post = postService.getById(id);
        model.addAttribute("post",post);
        return "publish";
    }

    @GetMapping("/publish")
    @ApiOperation(value = "跳转发布页")
    public String publish() {
        return "publish";
    }

    /**
     * post 中已包含 title、description、tag，
     * commentCount、viewCount、likeCount默认为0
     *
     * @param post
     * @return
     */
    @PostMapping("/publish")
    @ApiOperation(value = "提交发布")
    public String doPulish(Post post,
                           HttpServletRequest request,
                           Model model) {
        // 后端验证 前端不输入传入为 ''
        if ("".equals(post.getTitle()) || "".equals(post.getDescription()) || "".equals(post.getTag())){
            if ("".equals(post.getTitle())) {
                model.addAttribute("error", "标题不能为空");
            }else if ("".equals(post.getDescription())){
                model.addAttribute("error", "描述不能为空");
            }else if ("".equals(post.getTag())){
                model.addAttribute("error", "请添加至少一个标签");
            }
            model.addAttribute("post",post);
            return "publish";
        }

        post.setCreator(((User)request.getSession().getAttribute("user")).getId());
        postService.createOrUpdate(post);
        return "redirect:/";
    }
}
