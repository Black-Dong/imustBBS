package top.codingdong.imustbbs.controller;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dong
 * @date 2020/2/22 9:41
 */
@Controller
@Api(tags = {"index"})
public class IndexController {

    @GetMapping("/index.html")
    public String index(){
        return "index";
    }
}
