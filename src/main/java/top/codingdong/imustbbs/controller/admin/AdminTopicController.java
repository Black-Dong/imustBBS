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
public class AdminTopicController {

    @Autowired
    TopicService topicService;

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
     * 修改帖子的置顶状态
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
     * 修改帖子的精品状态
     *
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
