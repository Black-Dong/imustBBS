package top.codingdong.imustbbs.DTO;

import lombok.Data;

/**
 * 修改密码DTO
 *
 * @author Dong
 * @date 2020/4/21 21:57
 */
@Data
public class RepasswordDTO {

    private String nowPassword;

    private String password;

    private String repassword;

}
