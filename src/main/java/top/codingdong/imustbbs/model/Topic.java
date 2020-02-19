package top.codingdong.imustbbs.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Dong
 * @date 2020/2/19 9:23
 */
@Table(name = "t_topic")
@Data
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;
    private Long createTime;
    private Long updateTime;
    private Long lastReplyTime;
    private Long categoryId;
    private Long userId;
}
