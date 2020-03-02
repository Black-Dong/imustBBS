package top.codingdong.imustbbs.controller;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.mapper.ReplyMapper;
import top.codingdong.imustbbs.po.Category;
import top.codingdong.imustbbs.po.Reply;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.service.CategoryService;
import top.codingdong.imustbbs.service.ReplyService;
import top.codingdong.imustbbs.service.TopicService;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/28 11:31
 */
@Controller
public class TopicController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {

        List<Category> categories = categoryService.selectTop5();
        model.addAttribute("categories", categories);

        Topic dbTopic = topicService.selectAndUserAndCategoryById(id);
        model.addAttribute("dbTopic", dbTopic);

        model.addAttribute("activeCategory", dbTopic.getCategory().getId());

        List<Reply> replies = replyService.selectAndUserByTopicId(id);
        model.addAttribute("dbReplies", replies);


        return "detail";
    }
}
