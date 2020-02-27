package top.codingdong.imustbbs.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 帖子类型
 *
 * @author Dong
 * @date 2020/2/27 14:28
 */
@Entity
@Table(name = "topic_type")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
@Data
public class TopicType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer topicTypeId;

    @Column(length = 200)
    @NotEmpty(message = "帖子类型不能为空")
    private String topicTypeName;

    @Column(length = 1000)
    @NotEmpty(message = "描述不能为空")
    private String remark;

    private Integer sort;


}
