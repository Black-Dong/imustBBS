package top.codingdong.imustbbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.codingdong.imustbbs.po.Category;
import top.codingdong.imustbbs.po.Reply;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.service.CategoryService;
import top.codingdong.imustbbs.service.ReplyService;
import top.codingdong.imustbbs.service.TopicService;

import java.util.List;

/**
 * 帖子控制类
 *
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


    /**
     * 跳转帖子详情
     * @param id 帖子id
     * @param model
     * @return 返回帖子详情页
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {

        // 根据帖子id查询该帖子详情，包括发帖人和所在分类
        Topic dbTopic = topicService.selectAndUserAndCategoryById(id);
        if (!dbTopic.getPublicStatus()){
            // todo: 应该抛出404异常
            return "redirect:/";
        }
        model.addAttribute("dbTopic", dbTopic);

        // 激活的分类的id
        model.addAttribute("activeCategory", dbTopic.getCategory().getId());

        // 查询5个分类，放在navigation
        List<Category> categories = categoryService.selectNavCategory();
        model.addAttribute("categories", categories);


        // 查询回复列表
        List<Reply> replies = replyService.listAndUserByTopicId(id);
        model.addAttribute("dbReplies", replies);

        return "detail";
    }
}
