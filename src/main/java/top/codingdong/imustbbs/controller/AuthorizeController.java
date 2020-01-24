package top.codingdong.imustbbs.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.codingdong.imustbbs.dto.GithubOAuthDto;
import top.codingdong.imustbbs.dto.GithubUser;
import top.codingdong.imustbbs.mapper.UserMapper;
import top.codingdong.imustbbs.model.User;
import top.codingdong.imustbbs.utils.GithubOAuthUtil;

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
    GithubOAuthUtil githubOAuthUtil;

    @Autowired
    private UserMapper userMapper;

    @Value("${github.client_id}")
    private String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;
    @Value("${github.redirect_uri}")
    private String redirectUri;

    @GetMapping("/callback")
    @ApiOperation(value = "授权登录申请后的操作")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        GithubOAuthDto githubOAuthDto = new GithubOAuthDto();
        githubOAuthDto.setClient_id(clientId);
        githubOAuthDto.setClient_secret(clientSecret);
        githubOAuthDto.setRedirect_uri(redirectUri);
        githubOAuthDto.setCode(code);
        githubOAuthDto.setState(state);
        String accessToken = githubOAuthUtil.getAccessToken(githubOAuthDto);
        GithubUser userInfo = githubOAuthUtil.getUserInfo(accessToken);

        if (userInfo != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(userInfo.getName());
            user.setAccountId(String.valueOf(userInfo.getId()));
            user.setCreateTime(System.currentTimeMillis());
            user.setUpdateTime(System.currentTimeMillis());
            userMapper.insertUser(user);

            // 登录成功，写入session（或redis中）与cookie
            response.addCookie(new Cookie("token", token));
            request.getSession().setAttribute("user", userInfo);

            return "redirect:/";
        } else {
            // 登录失败，重新登录
            return "redirect:/";
        }
    }
}
