package top.codingdong.imustbbs.po;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;


/**
 * 帖子实体类
 *
 * @author Dong
 * @date 2020/2/27 15:02
 */
@Table(name = "t_topic")
@Data
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "帖子标题不能为空")
    private String title;

    @Lob
    private String content;

    private Long createTime;
    private Long updateTime;
    private Long lastReplyTime;

    @NotEmpty(message = "帖子必须有分类")
    private Long categoryId;
    @NotEmpty(message = "发帖人不能为空")
    private Long userId;

    @Column(columnDefinition = "public_status")
    private Boolean publicStatus = true;
    private Boolean topStatus = false;
    private Boolean boutiqueStatus = false;


    private User user;
    private Category category;

    @Transient
    private Integer replyCount;

}
