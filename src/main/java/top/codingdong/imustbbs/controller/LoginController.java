package top.codingdong.imustbbs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.codingdong.imustbbs.utils.GithubOAuthUtil;

/**
 * @author Dong
 * @date 2020/1/24 9:36
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    GithubOAuthUtil githubOauth;

    @GetMapping("/github")
    public String githubLogin() {
        String authorize = githubOauth.authorize();
        return "redirect:" + authorize;
    }
}
