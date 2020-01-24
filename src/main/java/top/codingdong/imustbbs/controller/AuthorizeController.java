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
import top.codingdong.imustbbs.utils.GithubOAuthUtil;

/**
 * @author Dong
 * @date 2020/1/24 10:46
 */
@Controller
@Api(description = "授权登录管理")
public class AuthorizeController {


    @Autowired
    GithubOAuthUtil githubOAuthUtil;

    @Value("${github.client_id}")
    private String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;
    @Value("${github.redirect_uri}")
    private String redirectUri;

    @GetMapping("/callback")
    @ApiOperation(value = "授权登录申请后的操作")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state) {
        GithubOAuthDto githubOAuthDto = new GithubOAuthDto();
        githubOAuthDto.setClient_id(clientId);
        githubOAuthDto.setClient_secret(clientSecret);
        githubOAuthDto.setRedirect_uri(redirectUri);
        githubOAuthDto.setCode(code);
        githubOAuthDto.setState(state);
        String accessToken = githubOAuthUtil.getAccessToken(githubOAuthDto);
        GithubUser userInfo = githubOAuthUtil.getUserInfo(accessToken);

        System.err.println(userInfo.getName());
        return "index";
    }
}
