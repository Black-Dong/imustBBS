package top.codingdong.imustbbs.enums;

/**
 * @author Dong
 * @date 2020/2/17 12:34
 */
public enum StatusEnum {

    SUCCESS(200, "操作成功"),
    LOGINUSER_NOT_EXIST(201, "用户未登录"),

    /*登录状态*/
    ACCOUNT_IS_EMPTY(2001, "用户名或邮箱不能为空！"),
    PASSWORD_IS_EMPTY(2002, "密码不能为空！"),
    USER_NOT_FOUND(2003, "账号或密码错误！"),

    /*注册状态*/
    MISSING_REGISTER_INFORMETION(2004, "注册信息不完整，请检查后重试！"),
    DIFFERENT_PASSWORD(2005, "两次密码输入不一致请重新输入"),
    USERNAME_EXIST(2006, "用户名已存在"),
    EMAIL_EXIST(2007, "邮箱已存在"),
    EMAIL_FORMAT_ERROR(2008, "邮箱格式不正确"),

    /*分类与标签状态*/
    CATEGORY_NAME_IS_EMPTY(2011, "名称不能为空"),
    CATEGORY_EXIST(2012, "分类已存在"),
    CATEGORY_NOT_EXIST(2013, "分类已不存在"),


    /*帖子状态*/
    TOPIC_TITLE_IS_EMPTY(2021,"帖子标题不能为空"),
    TOPIC_CONTENT_IS_EMPTY(2022,"帖子内容不能为空"),
    TOPIC_CATEGORY_IS_EMPTY(2023,"请选择帖子分类"),
    TOPIC_NOT_EXIST(2024,"帖子已不存在"),
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
