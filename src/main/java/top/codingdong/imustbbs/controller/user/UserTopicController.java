package top.codingdong.imustbbs.controller.user;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
 * 用户帖子控制类
 *
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

        // 设置帖子最后更新时间
        topic.setUpdateTime(System.currentTimeMillis());

        // 设置发帖人
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        topic.setUserId(user.getUserId().longValue());

        System.err.println(topic);

        // 根据是否有Id，判断是新增还是修改
        if (topic.getId() != null) {
            // 修改帖子，设置帖子最后更新时间
            topic.setUpdateTime(System.currentTimeMillis());
            topicService.update(topic);
        } else {
            // 新增帖子，设置帖子创建时间 和 最后回复时间
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
    @GetMapping("/manager/{managerId}/{pageNumber}")
    public String topicManager(@PathVariable Integer managerId,
                               @PathVariable Integer pageNumber,
                               Model model, HttpSession session) {
        User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);
        List list = null;
        // 帖子管理
        if (managerId == 2) {
            list = topicService.list(pageNumber, 10, currentUser.getUserId());

            PageInfo pageInfo = PageInfo.of(list);
            model.addAttribute("pageInfo", pageInfo);

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
