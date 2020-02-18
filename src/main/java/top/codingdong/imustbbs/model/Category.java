package top.codingdong.imustbbs.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Dong
 * @date 2020/2/18 11:05
 */
@Data
@Table(name = "t_category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long createTime;
    private Long updateTime;
}
