package top.codingdong.imustbbs.controller.own;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/publish")
    @ApiOperation(value = "跳转发布页")
    public String publish(@RequestParam(required = false) Post post,
                          Model model,
                          HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        if (post!=null){
            model.addAttribute("post",post);
        }
        return "publish";
    }

    /**
     * post 中已包含 title、description、tag，
     * commentCount、viewCount、likeCount默认为0
     *
     * @param post
     * @param request
     * @return
     */
    @PostMapping("/publish")
    @ApiOperation(value = "提交发布")
    public String doPulish(Post post,
                           HttpServletRequest request,
                           Model model) {
        try {
            User user = (User) request.getSession().getAttribute("user");
            post.setCreator(user.getId());
        } catch (NullPointerException e) {
            model.addAttribute("error", "用户未登录！");
            return "publish";
        }
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

        post.setCreateTime(System.currentTimeMillis());
        post.setUpdateTime(System.currentTimeMillis());

        postService.create(post);
        return "redirect:/";
    }
}
