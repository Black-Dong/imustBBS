package top.codingdong.imustbbs.dto;

import lombok.Data;
import top.codingdong.imustbbs.model.User;

/**
 * @author Dong
 * @date 2020/2/11 22:54
 */
@Data
public class CommentDto {

    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long createTime;
    private Long updateTime;
    private Long likeCount;
    private String content;

    private User user;
}
