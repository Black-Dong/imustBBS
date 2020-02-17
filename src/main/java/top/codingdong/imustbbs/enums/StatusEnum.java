package top.codingdong.imustbbs.enums;

/**
 * @author Dong
 * @date 2020/2/17 12:34
 */
public enum StatusEnum {

    ACCOUNT_IS_EMPTY(2001,"用户名或邮箱不能为空！"),
    PASSWORD_IS_EMPTY(2002,"密码不能为空！"),
    USER_NOT_FOUND(2003,"账号或密码错误！"),
    ;
    Integer code;
    String message;

    StatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
