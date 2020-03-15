package top.codingdong.imustbbs.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
     * 首页
     *
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {

        // 查询5个分类，放在navigation
        List<Category> categories = categoryService.selectTop5();
        model.addAttribute("categories", categories);

        // 查询帖子列表
        List<Topic> topics = topicService.listAndUserAndCategory(1, 10);
        PageInfo<Topic> pageInfo = PageInfo.of(topics);
        model.addAttribute("pageInfo", pageInfo);

        // 激活分类为 0 （首页）
        model.addAttribute("activeCategory", 0);

        // 返回首页
        return "index";
    }
}
