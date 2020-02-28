package top.codingdong.imustbbs.po;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private boolean publicStatus = true;



    private User user;
    private Category category;

}
