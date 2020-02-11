package top.codingdong.imustbbs.model;

import lombok.Data;

/**
 * @author Dong
 * @date 2020/2/11 22:54
 */
@Data
public class Comment {

    private Long id;
    private Long parentId;
    private int type;
    private Long commentator;
    private Long createTime;
    private Long updateTime;
    private Long likeCount;
    private String content;
}
