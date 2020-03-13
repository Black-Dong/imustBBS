package top.codingdong.imustbbs.controller.user;

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
import top.codingdong.imustbbs.service.CategoryService;
import top.codingdong.imustbbs.service.TopicService;
import top.codingdong.imustbbs.service.UserService;
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
    CategoryService categoryService;

    @Autowired
    UserService userService;


    /**
     * 跳转帖子发布页面
     *
     * @param model
     * @return
     */
    @GetMapping("/publishTopic")
    public String publishTopic(Model model) {
        List<Category> categories = categoryService.selectAll();
        model.addAttribute("categorys", categories);
        return "/user/publishTopic";
    }

    /**
     * 帖子发布与修改
     *
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
        if (topic.getId() != null) {
            topic.setUpdateTime(System.currentTimeMillis());
            topicService.update(topic);
        } else {
            topic.setCreateTime(System.currentTimeMillis());
            topic.setLastReplyTime(System.currentTimeMillis());
            topicService.save(topic);
        }

        // 跳转到帖子详情页
        return "redirect:/detail/" + topic.getId();
    }

    /**
     * 跳转帖子管理页面
     *
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/manager/{managerId}")
    public String topicManager(@PathVariable Integer managerId,
                               Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);
        List list = null;
        if (managerId == 2) {
            list = topicService.list(1, 10, currentUser.getUserId().longValue());
            model.addAttribute("myTopics", list);
            model.addAttribute("activeManager", managerId);
            return "/user/myTopicManager";
        }

        if (managerId == 5) {
            list = userService.selectAllmember();
            model.addAttribute("allUser", list);
            model.addAttribute("activeManager", managerId);
            return "/user/userManager";
        }

        return "/user/myTopicManager";
    }

    /**
     * 跳转帖子修改页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editTopic/{id}")
    public String publishTopic(@PathVariable Integer id, Model model) {
        Topic dbTopic = topicService.findById(id);
        model.addAttribute("dbTopic", dbTopic);
        List<Category> categories = categoryService.selectAll();
        model.addAttribute("categorys", categories);

        return "/user/editTopic";
    }
}
