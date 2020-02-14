package top.codingdong.imustbbs.dto;

import lombok.Data;
import top.codingdong.imustbbs.model.User;

/**
 * @author Dong
 * @date 2020/1/25 14:13
 */
@Data
public class PostDto {

    private Long id;
    private String title;
    private String description;
    private Long createTime;
    private Long updateTime;
    private Long creator;
    private Long commentCount;
    private Long viewCount;
    private Long likeCount;
    private String tag;

    private User user;
}
