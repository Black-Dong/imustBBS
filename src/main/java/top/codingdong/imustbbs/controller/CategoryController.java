package top.codingdong.imustbbs.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.codingdong.imustbbs.po.Category;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.service.CategoryService;
import top.codingdong.imustbbs.service.TopicService;

import java.util.List;

/**
 * 分类控制类
 *
 * @author Dong
 * @date 2020/2/29 20:19
 */
@Controller
public class CategoryController {

    @Autowired
    TopicService topicService;

    @Autowired
    CategoryService categoryService;


    /**
     * 根据 分类id 和 页数 查询该分类下的所有帖子
     *
     * @param id       分类id
     * @param model
     * @param pageNum  页码
     * @param pageSize
     * @return 返回首页
     */
    @GetMapping("/category/{id}/{pageNum}")
    public String category(@PathVariable Integer id, Model model,
                           @PathVariable Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {

        // 查询导航的5个分类名称
        List<Category> categories = categoryService.selectNavCategory();
        model.addAttribute("categories", categories);

        List<Topic> topics;
        // 查询该分类下所有帖子
        if (id == 0) {
            // 当id为0时，在 所有分类 中查询
            topics = topicService.listAndUserAndCategory(pageNum, pageSize);
        } else {
            // 根据分类id查询 该分类 下的所有帖子
            topics = topicService.listAndUserAndCategoryByCategoryId(pageNum, pageSize, id);
        }
        // 当前激活的分类id
        model.addAttribute("activeCategory", id);

        // 将贴子放入PageInfo返回
        PageInfo<Topic> pageInfo = PageInfo.of(topics);
        model.addAttribute("pageInfo", pageInfo);

        // 返回首页
        return "index";
    }
}
