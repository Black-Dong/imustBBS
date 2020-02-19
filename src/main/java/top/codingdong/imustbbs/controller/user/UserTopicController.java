package top.codingdong.imustbbs.controller.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import top.codingdong.imustbbs.DTO.ResultDTO;
import top.codingdong.imustbbs.enums.StatusEnum;
import top.codingdong.imustbbs.model.Topic;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.TopicService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Dong
 * @date 2020/2/19 9:32
 */
@Controller
@Api(tags = {"user_topic"})
@RequestMapping("/user")
public class UserTopicController {

    @Autowired
    private TopicService topicService;


    @GetMapping("/topics.html")
    @ApiOperation("跳转帖子列表页")
    public String jumpTopics(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                             Model model) {
        return "user/topics";
    }

    @GetMapping("/topic.html")
    @ApiOperation("跳转帖子新增页")
    public String jumpAddTopic() {
        return "user/topic-input";
    }

    @GetMapping("/topic/{id}")
    @ApiOperation("跳转帖子修改页")
    public String jumpEditTopic(@PathVariable(name = "id") Integer id,
                                RedirectAttributes redirectAttributes,
                                Model model) {

        Topic dbTopic = topicService.findTopicById(id);

        if (dbTopic == null) {
            redirectAttributes
                    .addFlashAttribute("resultDTO", ResultDTO.warnOf(StatusEnum.TOPIC_NOT_EXIST));
        }
        model.addAttribute("topic",dbTopic);
        return "user/topic-input";
    }

    @PostMapping("/topic")
    @ApiOperation("新增和修改帖子")
    @ResponseBody
    public ResultDTO topic(Topic topic,
                           HttpServletRequest request) {

        if (StringUtils.isBlank(topic.getTitle())) {
            return ResultDTO.warnOf(StatusEnum.TOPIC_TITLE_IS_EMPTY, topic);
        }
        if (StringUtils.isBlank(topic.getContent())) {
            return ResultDTO.warnOf(StatusEnum.TOPIC_CONTENT_IS_EMPTY);
        }
        if (topic.getCategoryId() == null) {
            return ResultDTO.warnOf(StatusEnum.TOPIC_CATEGORY_IS_EMPTY);
        }


        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            /*
                todo 用户设置初始值，
                 需要在过滤器中提前验证用户是否存在，不存在此时
                 model.addAttribute("resultDTO",ResultDTO.warnOf(StatusEnum.LOGINUSER_NOT_EXIST))
             */
            loginUser = new User();
            loginUser.setId(4L);
        }
        topic.setUserId(loginUser.getId());

        if (topic.getId() != null) {
            return topicService.updateTopic(topic);
        } else {
            return topicService.createTopic(topic);
        }

    }
}
