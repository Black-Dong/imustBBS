package top.codingdong.imustbbs.controller;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import top.codingdong.imustbbs.mapper.CategoryMapper;
import top.codingdong.imustbbs.po.Category;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.service.TopicService;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/26 13:15
 */
@Controller
public class IndexController {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private TopicService topicService;

    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/")
    public String index(Model model) {

        PageHelper.startPage(1, 5);
        List<Category> categories = categoryMapper.selectAll();
        model.addAttribute("categories", categories);

        List<Topic> topics = topicService.listAndUserAndCategory(1, 999);
        model.addAttribute("topics", topics);

        return "index";
    }
}
