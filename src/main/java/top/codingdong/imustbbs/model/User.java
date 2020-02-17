package top.codingdong.imustbbs.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Dong
 * @date 2020/2/17 12:09
 */
@Table(name = "t_user")
@Data
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    private String avatar;
    private String email;
    private String nickName;
    private String username;
    private String password;
    private Integer type;
    private Long createTime;
    private Long updateTime;
}
