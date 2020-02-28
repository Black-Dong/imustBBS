package top.codingdong.imustbbs.po;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author Dong
 * @date 2020/2/27 18:07
 */
@Table(name = "t_category")
@Data
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "分类名不能为空")
    private String name;
    private Long createTime;
    private Long updateTime;

    private String description;
}
