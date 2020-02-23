package top.codingdong.imustbbs.DTO;

import lombok.Data;

/**
 * @author Dong
 * @date 2020/2/23 12:06
 */
@Data
public class RegisterDTO {

    private String username;
    private String email;
    private String registerPassword;
    private String repeatPassword;
}
