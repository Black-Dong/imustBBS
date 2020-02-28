package top.codingdong.imustbbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tk.mybatis.mapper.entity.Example;
import top.codingdong.imustbbs.mapper.ReplyMapper;
import top.codingdong.imustbbs.po.Reply;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.service.TopicService;

import java.util.List;

/**
 * @author Dong
 * @date 2020/2/28 11:31
 */
@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private ReplyMapper replyMapper;


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model) {
        Topic dbTopic = topicService.selectAndUserAndCategoryById(id);
        model.addAttribute("dbTopic", dbTopic);

        Example replyExample = new Example(Reply.class);
        replyExample.createCriteria()
                .andEqualTo("topicId", dbTopic.getId());
        List<Reply> replies = replyMapper.selectByExample(replyExample);
        model.addAttribute("dbReplies", replies);

        return "detail";
    }
}
