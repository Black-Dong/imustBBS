package top.codingdong.imustbbs.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codingdong.imustbbs.mapper.TopicMapper;

/**
 * @author Dong
 * @date 2020/2/19 9:32
 */
@Controller
@RequestMapping("/user")
public class TopicController {

    @Autowired
    private TopicMapper topicMapper;


    @GetMapping("/topic")
    public String jumpAddTopic(){
        return "user/topic-input";
    }
}
