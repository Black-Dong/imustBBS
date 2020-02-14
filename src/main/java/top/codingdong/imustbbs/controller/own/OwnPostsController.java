package top.codingdong.imustbbs.controller.own;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.codingdong.imustbbs.dto.PostDto;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.PostService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dong
 * @date 2020/1/28 12:16
 */
@Controller
@RequestMapping("/own")
@Api(description = "我的帖子")
public class OwnPostsController {

    @Autowired
    private PostService postService;

    /**
     *
     * @param action    ownPosts: 我的帖子  replies: 最新回复
     * @param model
     * @param request
     * @param pageNumber
     * @param size
     * @return
     */
    @GetMapping("/{action}")
    @ApiOperation(value = "我的帖子中的各个链接 eg:我的帖子、最新回复")
    public String ownPosts(@PathVariable(name = "action") String action,
                           Model model,
                           HttpServletRequest request,
                           @RequestParam(name = "pageNumber",defaultValue = "1")Integer pageNumber,
                           @RequestParam(name = "size",defaultValue = "5")Integer size) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }

        if ("ownPosts".equals(action)) {
            model.addAttribute("section", "ownPosts");
            model.addAttribute("sectionName", "我的帖子");
            PageInfo<PostDto> pageInfo = postService.listByUserId(user.getId(), pageNumber, size);
            model.addAttribute("pageInfo", pageInfo);
        } else if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        return "ownPosts";
    }
}
