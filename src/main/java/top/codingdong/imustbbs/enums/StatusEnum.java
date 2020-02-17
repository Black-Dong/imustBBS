package top.codingdong.imustbbs.enums;

/**
 * @author Dong
 * @date 2020/2/17 12:34
 */
public enum StatusEnum {

    /*登录状态*/
    ACCOUNT_IS_EMPTY(2001,"用户名或邮箱不能为空！"),
    PASSWORD_IS_EMPTY(2002,"密码不能为空！"),
    USER_NOT_FOUND(2003,"账号或密码错误！"),

    /*注册状态*/
    MISSING_REGISTER_INFORMETION(2004,"注册信息不完整，请检查后重试！"),
    DIFFERENT_PASSWORD(2005,"两次密码输入不一致请重新输入"),
    USERNAME_EXIST(2006,"用户名已存在"),
    EMAIL_EXIST(2007,"邮箱已存在"),
    EMAIL_FORMAT_ERROR(2008,"邮箱格式不正确"),
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
