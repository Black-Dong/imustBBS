package top.codingdong.imustbbs.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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


    @GetMapping("/category/{id}/{pageNum}")
    public String category(@PathVariable Integer id, Model model,
                           @PathVariable Integer pageNum,
                           @RequestParam(defaultValue = "1") Integer pageSize) {

        PageHelper.startPage(1, 5);
        List<Category> categories = categoryMapper.selectAll();
        model.addAttribute("categories", categories);

        List<Topic> topics;
        if (id == 0) {
            topics = topicService.listAndUserAndCategory(pageNum, pageSize);
        } else {
            topics = topicService.listAndUserAndCategoryByCategoryId(pageNum, pageSize, id);
        }
        model.addAttribute("activeCategory", id);

        PageInfo<Topic> pageInfo = PageInfo.of(topics);
        model.addAttribute("pageInfo", pageInfo);


        return "index";
    }
}
