package top.codingdong.imustbbs.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.codingdong.imustbbs.po.Category;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.service.CategoryService;
import top.codingdong.imustbbs.service.TopicService;

import java.util.List;

/**
 * 首页控制类
 *
 * @author Dong
 * @date 2020/2/26 13:15
 */
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TopicService topicService;

    /**
     * 跳转首页
     *
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {

        // 查询标签中的分类
        List<Category> categories = categoryService.selectNavCategory();
        model.addAttribute("categories", categories);

        // 查询更多分类，不在标签中的分类
//        List<Category> moreCategorys = categoryService.selectMoreCategorys();

        // 查询帖子列表
        List<Topic> topics = topicService.listAndUserAndCategory(1, 10);
        PageInfo<Topic> pageInfo = PageInfo.of(topics);
        model.addAttribute("pageInfo", pageInfo);

        // 激活分类为 0 （首页）
        model.addAttribute("activeCategory", 0);

        // 返回首页
        return "index";
    }

    /**
     * 跳转登录页
     *
     * @return
     */
    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }

    /**
     * 跳转注册页
     *
     * @return
     */
    @GetMapping("/register")
    public String register() {
        return "/user/register";
    }

    /**
     * 跳转密码找回页
     *
     * @return
     */
    @GetMapping("/findPassword")
    public String findPassword() {
        return "/user/findPassword";
    }


    /**
     * 跳转搜索页
     *
     * @param title
     * @param model
     * @return
     */
    @RequestMapping("/search/{pageNumber}")
    public String search(String title, @PathVariable(name = "pageNumber") Integer pageNumber, Model model) {

        model.addAttribute("searchTitle", title);

        List<Topic> likeTopics = topicService.listLikeTitle(title, pageNumber);

        PageInfo<Topic> pageInfo = PageInfo.of(likeTopics);

        model.addAttribute("pageInfo", pageInfo);

        return "/search";
    }
}
