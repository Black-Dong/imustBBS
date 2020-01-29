package top.codingdong.imustbbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.codingdong.imustbbs.dto.GithubUser;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.service.UserService;
import top.codingdong.imustbbs.service.impl.GithubAuthorizeServiceImpl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author Dong
 * @date 2020/1/24 10:46
 */
@Controller
@Api(description = "授权登录管理")
public class AuthorizeController {

    @Autowired
    private GithubAuthorizeServiceImpl githubAuthorizeServiceImpl;

    @Autowired
    private UserService userService;

    @GetMapping("/github/callback")
    @ApiOperation(value = "github授权登录申请后的操作")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {

        GithubUser userInfo = githubAuthorizeServiceImpl.getGithubUser(code, state);

        if (userInfo != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(userInfo.getName());
            user.setAccountId(String.valueOf(userInfo.getId()));
            user.setSource("github");
            user.setAvatarUrl(userInfo.getAvatarUrl());
            // 向user表中插入或修改用户
            user = userService.createUser(user);
            System.out.println(user.getId());
            // 登录成功，写入session（或redis中）与cookie
            response.addCookie(new Cookie("token", token));
            request.getSession().setAttribute("user", user);

            return "redirect:/";
        } else {
            // 登录失败，重新登录
            return "redirect:/";
        }
    }



    @GetMapping("/logout")
    @ApiOperation(value = "退出登录")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().invalidate();
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }
}
