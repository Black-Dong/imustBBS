package top.codingdong.imustbbs.model;

import lombok.Data;

/**
 * @author Dong
 * @date 2020/1/24 15:39
 */
@Data
public class User {

    private Long id;
    private String name;
    private String accountId;
    private String source;
    private String avatarUrl;
    private String token;
    private Long createTime;
    private Long updateTime;
    private String email;
    private String password;

}
