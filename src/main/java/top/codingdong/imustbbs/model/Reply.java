package top.codingdong.imustbbs.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Dong
 * @date 2020/2/21 17:04
 */
@Table(name = "t_reply")
@Data
public class Reply {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private Long topicId;
    private String content;
    private Long userId;
    private String username;

    private Integer replyType;
    private Long fatherReplyId;
    private Long fatherUserId;
    private String fatherUserName;
    private Long createTime;
}
