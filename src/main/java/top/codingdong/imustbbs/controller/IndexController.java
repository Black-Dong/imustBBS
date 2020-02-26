package top.codingdong.imustbbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dong
 * @date 2020/2/26 13:15
 */
@Controller
public class IndexController {

    /**
     * 首页
     * @return
     */
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
