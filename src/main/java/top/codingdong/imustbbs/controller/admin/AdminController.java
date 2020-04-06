package top.codingdong.imustbbs.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
     * @param topicId
     */
    @GetMapping("/deleteTopic")
    public void deleteTopic(@RequestParam(name = "id") Integer topicId) {
        topicService.deleteById(topicId);
        return;
    }

}
