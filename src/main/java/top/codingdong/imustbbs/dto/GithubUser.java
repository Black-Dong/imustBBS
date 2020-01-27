package top.codingdong.imustbbs.dto;

import lombok.Data;

/**
 * @author Dong
 * @date 2020/1/24 12:53
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String avatarUrl;
    private String bio;

}
