package top.codingdong.imustbbs.dto;

import lombok.Data;

/**
 * @author Dong
 * @date 2020/1/24 11:09
 */
@Data
public class GithubOAuthDto {

    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String code;
    private String state;
}
