package top.codingdong.imustbbs.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import top.codingdong.imustbbs.service.TopicService;
import top.codingdong.imustbbs.service.UserService;

/**
 * 管理员控制类
 *
 * @author Dong
 * @date 2020/3/7 21:03
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    TopicService topicService;

    /**
     * 禁用用户
     *
     * @param userId
     */
    @GetMapping("/disableUser")
    @ResponseBody
    public void disableUser(@RequestParam(name = "userId") Integer userId) {
        userService.disableUser(userId);
    }

    /**
     * 解除禁用用户
     *
     * @param userId
     */
    @GetMapping("/unDisableUser")
    @ResponseBody
    public void unDisableUser(@RequestParam(name = "userId") Integer userId) {
        userService.unDisableUser(userId);
    }

    /**
     * 根据帖子id删除帖子，伪删除（修改帖子状态为不公开）
     *
     * @param topicId
     */
    @GetMapping("/deleteTopic")
    @ResponseBody
    public void deleteTopic(@RequestParam(name = "id") Integer topicId) {
        topicService.deleteById(topicId);
        return;
    }


    /**
     * 修改帖子 置顶状态
     *
     * @param topicId
     * @param topStatus
     */
    @GetMapping("/topTopic")
    @ResponseBody
    public void topTopic(@RequestParam(name = "id") Integer topicId,
                         @RequestParam("topStatus") boolean topStatus) {

        topicService.topTopicById(topicId, topStatus);
        return;
    }

    /**
     * 修改帖子 精品状态
     * @param topicId
     * @param boutiqueStatus
     */
    @GetMapping("/boutiqueTopic")
    @ResponseBody
    public void boutiqueTopic(@RequestParam(name = "id") Integer topicId,
                              @RequestParam("boutiqueStatus") boolean boutiqueStatus) {

        topicService.boutiqueTopicById(topicId, boutiqueStatus);
        return;
    }

}
