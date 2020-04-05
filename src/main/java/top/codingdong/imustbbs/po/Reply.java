package top.codingdong.imustbbs.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Dong
 * @date 2020/2/28 17:33
 */
@Table(name = "t_reply")
@Data
public class Reply implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer topicId;

    @Lob
    private String content;

    private Integer userId;

    private Integer replyType;

    private Integer fatherReplyId;

    private Integer fatherUserId;

    @JsonFormat(pattern = "yyyy-MM-d HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Long createTime;


    private User user;
    private Topic topic;
}
