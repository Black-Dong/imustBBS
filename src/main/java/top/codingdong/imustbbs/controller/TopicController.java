package top.codingdong.imustbbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import top.codingdong.imustbbs.po.Topic;
import top.codingdong.imustbbs.service.TopicService;

/**
 * @author Dong
 * @date 2020/2/28 11:31
 */
@Controller
public class TopicController {

    @Autowired
    private TopicService topicService;


    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model){
        Topic dbTopic = topicService.selectAndUserAndCategoryById(id);
        model.addAttribute("dbTopic",dbTopic);
        return "detail";
    }
}
