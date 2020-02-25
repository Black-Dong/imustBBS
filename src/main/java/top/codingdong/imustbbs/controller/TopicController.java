package top.codingdong.imustbbs.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import top.codingdong.imustbbs.service.TopicService;

/**
 * @author Dong
 * @date 2020/2/19 10:16
 */
@Controller
@Api(tags = {"topic"})
public class TopicController {

    @Autowired
    private TopicService topicService;


    @GetMapping("/topics")
    @ApiOperation("获取帖子列表")
    @ResponseBody
    public PageInfo topics(@RequestParam(name = "pageNumber", defaultValue = "1") Integer pageNumber,
                           @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                           @RequestParam(name = "categoryId", defaultValue = "0") Long category) {


        if (category == 0) {
            return PageInfo.of(topicService.findAll(pageNumber, pageSize));
        }
        return PageInfo.of(topicService.findsByCategoryId(pageNumber, pageSize, category));

    }
}
