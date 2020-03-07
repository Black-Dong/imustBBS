package top.codingdong.imustbbs.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author Dong
 * @JsonIgnoreProperties() 将这个注解写在类上之后，会忽略类中不存在的字段
 * @date 2020/2/26 15:10
 */
@Data
@Entity
@Table(name = "t_user")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    /**
     * QQ用户唯一标识
     */
    @Column(length = 200)
    private String openId;

    @Column(length = 200)
    @NotEmpty(message = "昵称不能为空")
    private String nickname;

    @Column(length = 100)
    @NotEmpty(message = "请输入用户名")
    private String username;

    @Column(length = 100)
    @NotEmpty(message = "请输入密码")
    private String password;

    @Column(length = 100)
    @Email(message = "邮箱地址格式错误")
    @NotEmpty(message = "请输入邮箱地址")
    private String email;


    @Column(length = 100)
    private String avatar;

    @Column(length = 50)
    private String sex;

    @Column
    private Integer lvGrade = 0;

    @Column
    private boolean isOff = false;

    @Column
    private String roleName = "会员";

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date latelyLoginTime;

    /**
     * @Transient 表示不是数据库字段
     */
    @Transient
    private Integer messageCount;
}
