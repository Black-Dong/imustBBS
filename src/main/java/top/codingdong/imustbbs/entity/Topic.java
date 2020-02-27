package top.codingdong.imustbbs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * @author Dong
 * @date 2020/2/27 15:02
 */
@Entity
@Table(name = "topic")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@Data
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topicId;

    @Column(length = 200)
    @NotEmpty(message = "帖子名称不能为空")
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;

    @Transient
    private String publishDateStr;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_type_id")
    private TopicType topicType;

    private boolean isFree = true;
    // 积分
    private Integer points;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
    // 无网页标签的content
    private String contentNoTag;

    @Column(length = 200)
    private String download;

    @Column(length = 10)
    private String password;

    private boolean isHot = false;
    private Integer state;
    // 驳回原因
    private String reason;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkDate;

    private Integer click;

    @Column(length = 200)
    private String keywords;

    @Column(length = 200)
    private String description;

    private boolean isUseful = true;

}
