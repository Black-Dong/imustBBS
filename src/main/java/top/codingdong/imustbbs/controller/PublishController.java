package top.codingdong.imustbbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import top.codingdong.imustbbs.mapper.PostMapper;
import top.codingdong.imustbbs.model.Post;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dong
 * @date 2020/1/25 10:24
 */
// todo: 需要再做一个权限控制，未登录不显示发布
@Controller
public class PublishController {

    @Autowired
    private PostService postService;

    @GetMapping("/publish")
    public String publish() {
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
    public String doPulish(Post post,
                           HttpServletRequest request,
                           Model model) {
        User user = (User) request.getSession().getAttribute("user");
        try {
            post.setCreator(user.getId());
        } catch (NullPointerException e) {
            model.addAttribute("error", "用户未登录！");
            return "publish";
        }
        if ("".equals(post.getTitle())) {
            model.addAttribute("error", "标题不能为空");
            model.addAttribute("post",post);
            return "publish";
        }else if ("".equals(post.getDescription())){
            model.addAttribute("error", "描述不能为空");
            model.addAttribute("post",post);
            return "publish";
        }

        post.setCreateTime(System.currentTimeMillis());
        post.setUpdateTime(System.currentTimeMillis());

        postService.create(post);
        return "redirect:/";
    }
}
