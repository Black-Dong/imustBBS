package top.codingdong.imustbbs.controller;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.codingdong.imustbbs.mapper.CategoryMapper;
import top.codingdong.imustbbs.po.Category;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.service.TopicService;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/29 20:19
 */
@Controller
public class CategoryController {

    @Autowired
    TopicService topicService;

    @Autowired
    private CategoryMapper categoryMapper;


    @GetMapping("/category/{id}")
    public String category(@PathVariable Integer id, Model model) {

        PageHelper.startPage(1, 5);
        List<Category> categories = categoryMapper.selectAll();
        model.addAttribute("categories", categories);
        List<Topic> topics = topicService.listAndUserAndCategoryByCategoryId(id);
        model.addAttribute("topics", topics);

        model.addAttribute("activeCategory", id);

        return "index";
    }
}
