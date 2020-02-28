package top.codingdong.imustbbs.controller.user;

import net.sf.jsqlparser.statement.select.Top;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codingdong.imustbbs.mapper.CategoryMapper;
import top.codingdong.imustbbs.po.Category;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.po.User;
import top.codingdong.imustbbs.service.TopicService;
import top.codingdong.imustbbs.util.Constants;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author Dong
 * @date 2020/2/27 17:57
 */
@Controller
@RequestMapping("/user")
public class UserTopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    CategoryMapper categoryMapper;


    /**
     * 跳转帖子发布页面
     * @param model
     * @return
     */
    @GetMapping("/publishTopic")
    public String publishTopic(Model model) {
        List<Category> categories = categoryMapper.selectAll();
        model.addAttribute("categorys", categories);
        return "/user/publishTopic";
    }

    /**
     * 帖子发布与修改
     * @param topic
     * @param session
     * @return
     */
    @PostMapping("/publishTopic")
    public String publishTopic(Topic topic, HttpSession session) {

        topic.setUpdateTime(System.currentTimeMillis());
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        topic.setUserId(user.getUserId().longValue());

        System.err.println(topic);
        if (topic.getId() != null){
            topic.setUpdateTime(System.currentTimeMillis());
            topicService.update(topic);
        }else {
            topic.setCreateTime(System.currentTimeMillis());
            topic.setLastReplyTime(System.currentTimeMillis());
            topicService.save(topic);
        }

        return "redirect:/user/topicManager";
    }

    /**
     * 跳转帖子管理页面
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/topicManager")
    public String topicManager(Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);
        List<Topic> list = topicService.list(1, 10, currentUser.getUserId().longValue());
        model.addAttribute("myTopics", list);
        return "/user/topicManager";
    }

    /**
     * 跳转帖子修改页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editTopic/{id}")
    public String publishTopic(@PathVariable Integer id, Model model) {
        Topic dbTopic = topicService.findById(id);
        model.addAttribute("dbTopic",dbTopic);
        List<Category> categories = categoryMapper.selectAll();
        model.addAttribute("categorys", categories);

        return "/user/editTopic";
    }
}
