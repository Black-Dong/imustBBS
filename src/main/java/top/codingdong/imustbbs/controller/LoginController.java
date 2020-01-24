package top.codingdong.imustbbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(description = "登录页")
public class LoginController {

    @Autowired
    GithubOAuthUtil githubOauth;

    @GetMapping("/github")
    @ApiOperation(value = "github第三方登录")
    public String githubLogin() {
        String authorize = githubOauth.authorize();
        return "redirect:" + authorize;
    }
}
