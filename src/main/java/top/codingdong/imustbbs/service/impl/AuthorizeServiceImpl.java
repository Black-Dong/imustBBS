package top.codingdong.imustbbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.codingdong.imustbbs.dto.GithubOAuthDto;
import top.codingdong.imustbbs.dto.GithubUser;
import top.codingdong.imustbbs.service.AuthorizeService;
import top.codingdong.imustbbs.utils.GithubOAuthUtil;

/**
 * @author Dong
 * @date 2020/1/26 13:52
 */
@Service
@Transactional
public class AuthorizeServiceImpl implements AuthorizeService {

    @Autowired
    GithubOAuthUtil githubOAuthUtil;

    @Value("${github.client_id}")
    private String clientId;
    @Value("${github.client_secret}")
    private String clientSecret;
    @Value("${github.redirect_uri}")
    private String redirectUri;

    @Override
    public GithubUser getGithubUser(String code,String state) {
        GithubOAuthDto githubOAuthDto = new GithubOAuthDto();
        githubOAuthDto.setClient_id(clientId);
        githubOAuthDto.setClient_secret(clientSecret);
        githubOAuthDto.setRedirect_uri(redirectUri);
        githubOAuthDto.setCode(code);
        githubOAuthDto.setState(state);
        String accessToken = githubOAuthUtil.getAccessToken(githubOAuthDto);
        GithubUser userInfo = githubOAuthUtil.getUserInfo(accessToken);
        return userInfo;
    }
}
