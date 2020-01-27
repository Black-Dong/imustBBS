package top.codingdong.imustbbs.model;

import lombok.Data;

/**
 * @author Dong
 * @date 2020/1/25 14:13
 */
@Data
public class Post {

    private Integer id;
    private String title;
    private String description;
    private Long createTime;
    private Long updateTime;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;

    private User user;
}
