package top.codingdong.imustbbs.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.codingdong.imustbbs.dto.PostDto;
import top.codingdong.imustbbs.service.PostService;

/**
 * @author Dong
 * @date 2020/1/24 0:30
 */
@Controller
@Api(description = "首页")
public class IndexController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    @ApiOperation("跳转首页")
    public String index(Model model,
                        @RequestParam(name = "pageNumber",defaultValue = "1")Integer pageSize,
                        @RequestParam(name = "size",defaultValue = "3")Integer size
                        ) {

        PageInfo<PostDto> pageInfo = postService.listPost(pageSize, size);
        model.addAttribute("pageInfo",pageInfo);

        return "index";
    }

}
