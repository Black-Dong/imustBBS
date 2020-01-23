package top.codingdong.imustbbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dong
 * @date 2020/1/24 0:30
 */
@Controller
@Api(description = "首页")
public class IndexController {

    @GetMapping("/")
    @ApiOperation("跳转首页")
    public String index() {
        return "index";
    }

}
