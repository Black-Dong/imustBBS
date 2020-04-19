package top.codingdong.imustbbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.codingdong.imustbbs.po.Reply;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.po.User;
import top.codingdong.imustbbs.service.ReplyService;
import top.codingdong.imustbbs.service.TopicService;
import top.codingdong.imustbbs.service.UserService;

import java.util.List;

/**
 * @author Dong
 * @date 2020/4/1 15:48
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyService replyService;

    /**
     * 用户主页
     *
     * @param id         用户Id
     * @param pageNumber 页数
     * @param model
     * @return
     */
    @GetMapping("/user/detail/{id}")
    public String userHome(@PathVariable Integer id,
                           @RequestParam(required = false, defaultValue = "1") Integer pageNumber,
                           Model model) {

        User dbUser = userService.selectById(id);
        List<Topic> dbTopics = topicService.list(pageNumber, 10, id);

        List<Reply> dbReplies = replyService.listAndTopicByUserId(dbUser.getUserId(), pageNumber, 4);


        model.addAttribute("user", dbUser);
        model.addAttribute("topics", dbTopics);
        model.addAttribute("replies", dbReplies);

        return "/user/userHome";
    }


}
